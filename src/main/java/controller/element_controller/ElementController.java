package controller.element_controller;

import controller.visualisation.painters.ElementPainter;
import model.elements.Edge;
import model.elements.Vertex;
import model.graphs.representation.Subgraph;
import model.settings.CurrentAction;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import view.BestPathManager;
import view.GraphPanel;

import java.awt.event.MouseEvent;

public class ElementController {
    private final GraphPanel panel;
    private ElementPainter elementPainter;
    private int flag1=0;
    private int flag2=0;

    public ElementController(GraphPanel panel){
        this.panel=panel;
        this.elementPainter=new ElementPainter(panel.getG(),panel);
    }

    public void action(MouseEvent e) {
        if (panel.getSettings().currently == CurrentAction.PATH) {
            Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam / 2, e.getY() - panel.getSettings().vdiam / 2);
            if (a != null&&!a.equals(panel.getCurrentVertex())) {
                if(flag1==0){
                    flag1++;
                    panel.setCurrentVertex(a);
                }
                else{
                    flag1=0;
                    visualizeShortestPath(a,panel.getCurrentVertex());
                }
            }
        }

        if (panel.getSettings().currently == CurrentAction.BESTPATH) {
            Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam / 2, e.getY() - panel.getSettings().vdiam / 2);
            if (a != null&&!a.equals(panel.getCurrentVertex())) {
                if(flag2==0){
                    flag2++;
                    panel.setCurrentVertex(a);
                }
                else{
                    flag2=0;
                    BestPathManager BM=new BestPathManager(this,a,panel.getCurrentVertex());
                    BM.setVisible(true);
                }
            }
        }
    }



    public void visualizeCenter(){
        if(panel.getG().getCenter()!=null){
            visualizeSubgraph(panel.getG().getCenter());
        }
    }

    public void visualizePeryfery(){
        if(panel.getG().getCenter()!=null){
            visualizeSubgraph(panel.getG().getPeryfery());
        }
    }

    public void visualizeShortestPath(Vertex u, Vertex v){
        System.out.println(u);
        if(panel.getG().connected(u,v)){
            visualizePath(panel.getG().getShortestPath(u,v));
        }
    }

    public void visualizeMinimalST(){
        try {
            panel.getG().getLogic().vkraskal(elementPainter);
            KruskalMinimumSpanningTree kruskal=new KruskalMinimumSpanningTree(panel.getG());
            SpanningTreeAlgorithm.SpanningTree<DefaultWeightedEdge> MST=
                    kruskal.getSpanningTree();
            panel.getMyParent().getInfo().setText("Total Weight="+MST.getWeight());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void visualizeMaxVerticesPath(Vertex u,Vertex v,int max){
        if(panel.getG().connected(u,v)){
            visualizePath(panel.getG().getMaxVertexPath(u,v,max));
        }
    }

    public void visualizeMaxVerticesWithLabelsPath(Vertex u,Vertex v,int max,String s){

    }

    public void visualizeMaxWeightPath(Vertex u,Vertex v){
        if(panel.getG().connected(u,v)){
            visualizePath(panel.getG().getMaxWeightPath(u,v));
        }
    }

    private void visualizePath(GraphPath<Vertex,Edge> path){
        System.out.println(path);
        if(!path.getVertexList().isEmpty()) {
            Vertex last=null;
            for (Vertex v : path.getVertexList()) {
                elementPainter.visualVertex(v.getNumb());
                if(last!=null) elementPainter.visualEdge(last.getNumb(),v.getNumb());
                last=v;
            }
//            for (DefaultWeightedEdge e : path.getEdgeList()) {
//                try {
//                    Field source=e.getClass().getClass().getDeclaredField("source");
//                    source.setAccessible(true);
//                    Vertex mySource=(Vertex) source.get(e);
//                    Field target=e.getClass().getClass().getDeclaredField("target");
//                    target.setAccessible(true);
//                    Vertex myTarget=(Vertex) target.get(e);
//                    elementPainter.visualEdge(mySource.getNumb(), myTarget.getNumb());
//                } catch (NoSuchFieldException e1) {
//                    e1.printStackTrace();
//                } catch (IllegalAccessException e1) {
//                    e1.printStackTrace();
//                }
//            }
        }
    }

    private void visualizeSubgraph(Subgraph G){
        if(!G.getVertexes().isEmpty()) {
            for (Vertex v : G.getVertexes()) {
                elementPainter.visualVertex(v.getNumb());
            }
            for (Edge e : G.getEdges()) {
                elementPainter.visualEdge(e.getMySource().getNumb(), e.getMyTarget().getNumb());
            }
        }
    }






}
