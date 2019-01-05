package controller.visualisation;

import model.elements.Edge;
import model.elements.Vertex;
import model.graphs.representation.SimpleGraph;
import view.GraphPanel;

public class Painter implements Visualable {

    public Painter(SimpleGraph g, GraphPanel panel) {
        G = g;
        this.panel=panel;
    }


    private final SimpleGraph G;
    private final GraphPanel panel;

    @Override
    public void visualVertex(int v){
        for (Vertex c : G.getVertexes()) {
            if (c.getNumb() == v) {
                c.setColor(panel.getSettings().colorEx);
                panel.update(panel.getGraphics());
            }
        }
    }

    @Override
    public void visualEdge(int a,int b){
        for (Edge c : G.getEdges()) {
            if ((c.getSource().getNumb() == a && c.getTarget().getNumb() == b) || (c.getTarget().getNumb() == a && c.getSource().getNumb() == b)) {
                c.setColor(panel.getSettings().colorEx);
                panel.update(panel.getGraphics());
            }
        }

    }
}
