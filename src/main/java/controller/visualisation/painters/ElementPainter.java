package controller.visualisation.painters;

import model.elements.Edge;
import model.elements.Vertex;
import model.graphs.representation.WeightGraph;
import view.GraphPanel;

public class ElementPainter implements Visualable {
    public ElementPainter(WeightGraph g, GraphPanel panel) {
        G = g;
        this.panel=panel;
    }

    private final WeightGraph G;
    private final GraphPanel panel;

    @Override
    public void visualVertex(int v,String ... arg){
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
            if ((c.getMySource().getNumb() == a && c.getMyTarget().getNumb() == b) || (c.getMyTarget().getNumb() == a && c.getMySource().getNumb() == b)) {
                c.setColor(panel.getSettings().colorEx);
                panel.update(panel.getGraphics());
            }
        }
    }


}
