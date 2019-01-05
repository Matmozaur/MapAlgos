package model.graphs.representation;

import model.settings.Settings;
import model.elements.Edge;
import model.elements.Vertex;
import model.graphs.logic.SimpleGraphLogic;
import view.GraphPanel;

import java.util.ListIterator;

public class SimpleGraph extends Graph {
    private final SimpleGraphLogic logic;

    SimpleGraph(GraphPanel panel){
        logic=new SimpleGraphLogic(this,0,new boolean[Settings.n][Settings.n]);
        this.panel=panel;
    }

    public SimpleGraphLogic getLogic() {
        return logic;
    }

    public void add(Vertex v){
        vertexes.add(v);
        logic.addV();
    }

    void add(Edge e){
        this.edges.add(e);
        logic.addE(e.getSource().getNumb(),e.getTarget().getNumb());
    }

    @Override
    void clear(){
        super.clear();
        for (Edge c : edges) {
            c.setColor(panel.getSettings().ecolor);
        }
    }

    void removeE(Edge e){
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

    void remove(Vertex v){
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

// --Commented out by Inspection START (05.01.19 22:10):
//    public String allInfo(){
//        return logic.allInfo();
//    }
// --Commented out by Inspection STOP (05.01.19 22:10)

}
