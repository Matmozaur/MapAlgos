package model.graphs.representation;

import model.elements.Edge;
import model.elements.Vertex;
import model.elements.WeightEdge;
import model.graphs.logic.WeightGraphLogic;
import model.settings.Settings;
import view.GraphPanel;

import java.util.ListIterator;

public class WeightGraph extends SimpleGraph {


    private final WeightGraphLogic logic;

    public WeightGraph(GraphPanel panel){
        super(panel);
        logic=new WeightGraphLogic(this,0,new boolean[Settings.n][Settings.n],new int[Settings.n][Settings.n]);
    }


    @Override
    public WeightGraphLogic getLogic() {
        return logic;
    }

    @Override
    public void add(Vertex v){
        super.add(v);
        vertexes.add(v);
        logic.addV();
    }

    @Override
    public void add(Edge e){
        super.add(e);
        logic.addE(e.getSource().getNumb(),e.getTarget().getNumb());
    }

    public void add(WeightEdge e){
        super.add(e);
        logic.addE(e.getSource().getNumb(),e.getTarget().getNumb(),e.getWeight());
    }

    @Override
    public void removeE(Edge e){
        super.removeE(e);
        int a;
        for (Edge f : edges) {
            if (f == e) {
                a = edges.indexOf(f);
                panel.unselect(e.getSource(), e.getTarget());
                edges.remove(a);
                logic.remove(e.getSource().getNumb(),e.getTarget().getNumb());
                break;
            }
        }
    }


    @Override
    public void clear(){
        super.clear();
    }

    @Override
    public void remove(Vertex v){
        super.remove(v);
        ListIterator<Edge> iterE = edges.listIterator();
        while (iterE.hasNext()) {
            Edge e = iterE.next();
            if (e.getSource() == v || e.getTarget() == v) {
                iterE.remove();
                panel.removeEdge(e);
            }
        }

        panel.setCounter(panel.getCounter()-1);
        ListIterator<Vertex> iterV = vertexes.listIterator();
        while (iterV.hasNext()) {
            Vertex u = iterV.next();
            if (u == v) {
                iterV.remove();
                logic.remove(v.getNumb());
            }
        }
        for (Vertex u : vertexes) {
            if (u.getNumb() > v.getNumb()) u.setNumb(u.getNumb() - 1);
        }

    }

    public String allInfo(){
        return logic.allInfo();
    }
}
