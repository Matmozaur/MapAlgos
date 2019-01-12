package model.graphs.representation;

import model.elements.Edge;
import model.elements.Vertex;
import model.elements.WeightEdge;
import model.graphs.logic.WeightGraphLogic;
import model.settings.Settings;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import view.GraphPanel;

import javax.swing.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class WeightGraph extends SimpleWeightedGraph<Vertex, DefaultWeightedEdge>  implements Serializable {
    private static final long serialVersionUID = -1567413557378365221L;

    private final List<Vertex> vertexes=new LinkedList<>();
    private final List<Edge> edges=new LinkedList<>();
    private transient GraphPanel panel;
    private final WeightGraphLogic logic;

    public WeightGraph(GraphPanel panel){
        super(DefaultWeightedEdge.class);
        this.panel=panel;
        logic=new WeightGraphLogic(this,0,new boolean[Settings.n][Settings.n],new int[Settings.n][Settings.n]);
    }
    public WeightGraph(){
        super(DefaultWeightedEdge.class);
        logic=new WeightGraphLogic(this,0,new boolean[Settings.n][Settings.n],new int[Settings.n][Settings.n]);
    }


    public void setPanel(GraphPanel panel) {
        this.panel = panel;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public WeightGraphLogic getLogic() {
        return logic;
    }

    public void add(Vertex v){
        super.addVertex(v);
        vertexes.add(v);
        logic.addV();
    }

    public void add(Edge e){
        super.addEdge(e.getMySource(),e.getMyTarget());
        this.edges.add(e);
        logic.addE(e.getMySource().getNumb(),e.getMyTarget().getNumb());
    }

    public void add(WeightEdge e){
        DefaultWeightedEdge f=super.addEdge(e.getMySource(),e.getMyTarget());
        super.setEdgeWeight(f,e.getMyWeight());
        this.edges.add(e);
        logic.addE(e.getMySource().getNumb(),e.getMyTarget().getNumb());
        logic.addE(e.getMySource().getNumb(),e.getMyTarget().getNumb(),e.getMyWeight());
    }

    public void removeE(Edge e){
        int a;
        for (Edge f : edges) {
            if (f.equals(e)) {
                a = edges.indexOf(f);
                if(panel!=null) panel.unselect(e.getMySource(), e.getMyTarget());
                edges.remove(a);
                logic.remove(e.getMySource().getNumb(),e.getMyTarget().getNumb());
                break;
            }
        }
    }

    public void clear(){
        for (Vertex c : vertexes) {
            c.setColor(panel.getSettings().vcolor);
            c.setAdditionalLabel(null);
        }
        for (Edge e : edges) {
            e.setColor(panel.getSettings().ecolor);
        }
    }

    public void remove(Vertex v){
        ListIterator<Edge> iterE = edges.listIterator();
        while (iterE.hasNext()) {
            Edge e = iterE.next();
            if (e.getMySource().equals(v) || e.getMyTarget().equals(v)) {
                iterE.remove();
                removeE(e);
                if(panel!=null) panel.removeEdge(e);
            }
        }
        if(panel!=null) panel.setCounter(panel.getCounter()-1);
        ListIterator<Vertex> iterV = vertexes.listIterator();
        while (iterV.hasNext()) {
            Vertex u = iterV.next();
            if (u.equals(v)) {
                iterV.remove();
                logic.remove(v.getNumb());
            }
        }
        for (Vertex u : vertexes) {
            if (u.getNumb() > v.getNumb()) u.setNumb(u.getNumb() - 1);
        }

    }

    @Override
    public boolean containsVertex(Vertex v){
        return vertexes.contains(v);
    }

    public String allInfo(){
        return logic.allInfo();
    }

    public String basicInfo(){
        return logic.basicInfo();
    }

    public String vertexInfo(Vertex v){
        return "Label="+v.getLabel()+" \n"+logic.vertexInfo(v.getNumb());
    }

    public boolean connected(Vertex v, Vertex u){
        return logic.connected(v.getNumb(),u.getNumb());
    }

    private Subgraph getInducedSubgraph(int[] A){
        if(A!=null) {
            Subgraph sub = new Subgraph();
            for (int i : A) {
                if (i >= 0) {
                    sub.getVertexes().add(this.vertexes.get(i));
                    sub.getEdges().addAll(this.edges.stream().
                            filter(e -> e.getMySource().getNumb() == i || e.getMyTarget().getNumb() == i).
                            filter(e->sub.getVertexes().contains(e.getMySource())&&
                                    sub.getVertexes().contains(e.getMyTarget())).
                            collect(Collectors.toList()));
                }
            }
            return sub;
        }
        return null;
    }

    public Subgraph getCenter(){
        return getInducedSubgraph(logic.getCenter());
    }

    public Subgraph getPeryfery(){
        return getInducedSubgraph(logic.getPeryfery());
    }

    public GraphPath getShortestPath(Vertex v, Vertex u){
        GraphPath shortest_path =   DijkstraShortestPath.findPathBetween(this, v, u);
        return shortest_path;
    }

    public GraphPath<Vertex,Edge> getMaxWeightPath(Vertex v,Vertex u){
        //noinspection unchecked
        DefaultDirectedWeightedGraph<Vertex,DefaultWeightedEdge> G=
                new DefaultDirectedWeightedGraph(DefaultWeightedEdge.class);
        for(Vertex x:this.getVertexes()){
            G.addVertex(x);
        }
        for(Edge e:this.getEdges()){
            DefaultWeightedEdge f=G.addEdge(e.getMySource(),e.getMyTarget());
            G.setEdgeWeight(f,e.getMyWeight());
            DefaultWeightedEdge k=G.addEdge(e.getMyTarget(),e.getMySource());
            G.setEdgeWeight(k,e.getMyWeight());
        }
        Set<Vertex> sourceVertices=new HashSet();
        Set<Vertex> targetVertices=new HashSet<>();
        sourceVertices.add(v);
        targetVertices.add(u);
        AllDirectedPaths<Vertex,Edge> AllP=new AllDirectedPaths(G);
        List<GraphPath<Vertex, Edge>> list=
                AllP.getAllPaths(sourceVertices,targetVertices,true,Integer.MAX_VALUE);
        return list.stream().max(Comparator.comparing(GraphPath::getWeight)).
                orElseThrow(NoSuchElementException::new);

    }

    public GraphPath<Vertex,Edge> getMaxVertexPath(Vertex v,Vertex u,int max){
        DefaultDirectedWeightedGraph<Vertex,DefaultWeightedEdge> G=
                new DefaultDirectedWeightedGraph(DefaultWeightedEdge.class);
        for(Vertex x:this.getVertexes()){
            G.addVertex(x);
        }
        for(Edge e:this.getEdges()){
            DefaultWeightedEdge f=G.addEdge(e.getMySource(),e.getMyTarget());
            G.setEdgeWeight(f,e.getMyWeight());
            DefaultWeightedEdge k=G.addEdge(e.getMyTarget(),e.getMySource());
            G.setEdgeWeight(k,e.getMyWeight());
        }
        Set<Vertex> sourceVertices=new HashSet();
        Set<Vertex> targetVertices=new HashSet<>();
        sourceVertices.add(v);
        targetVertices.add(u);
        AllDirectedPaths<Vertex,Edge> AllP=new AllDirectedPaths(G);
        List<GraphPath<Vertex, Edge>> list=
                AllP.getAllPaths(sourceVertices,targetVertices,true,max);
        list=list.stream().filter(p->p.getWeight()<=max).collect(Collectors.toList());
        try {
            return list.stream().max(Comparator.comparing(GraphPath::getLength)).
                    orElseThrow(NoSuchElementException::new);
        }
        catch (NoSuchElementException e){
            JOptionPane.showMessageDialog(panel,"There is no such a path!");
        }
        return null;
    }



}
