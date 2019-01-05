package controller.visualisation;

import model.elements.Edge;
import model.elements.Vertex;
import model.graphs.representation.SimpleGraph;
import view.GraphPanel;

public class SlowPainter implements Visualable {
    SlowPainter(SimpleGraph g, GraphPanel panel) {
        G = g;
        this.panel=panel;
    }

    private final SimpleGraph G;
    private final GraphPanel panel;

    @Override
    public void visualVertex(int v) throws InterruptedException {
        for (Vertex c : G.getVertexes()) {
            if (c.getNumb() == v) {
                c.setColor(panel.getSettings().colorEx);
                panel.update(panel.getGraphics());
                Thread.sleep(1000,1);
            }
        }
    }

    @Override
    public void visualEdge(int a,int b) throws InterruptedException {
        for (Edge c : G.getEdges()) {
            if ((c.getSource().getNumb() == a && c.getTarget().getNumb() == b) || (c.getTarget().getNumb() == a && c.getSource().getNumb() == b)) {
                c.setColor(panel.getSettings().colorEx);
                panel.update(panel.getGraphics());
                Thread.sleep(panel.getSettings().t);
            }
        }

    }
}
