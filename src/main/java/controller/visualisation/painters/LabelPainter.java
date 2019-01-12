package controller.visualisation.painters;

import model.elements.Edge;
import model.elements.Vertex;
import model.graphs.representation.WeightGraph;
import view.GraphPanel;

public class LabelPainter implements Visualable {

    public LabelPainter(WeightGraph g, GraphPanel panel) {
        G = g;
        this.panel=panel;
    }


    private final WeightGraph G;
    private final GraphPanel panel;

    @Override
    public void visualVertex(int v,String ... arg) throws InterruptedException {
        for (Vertex c : G.getVertexes()) {
            if (c.getNumb() == v) {
                c.setColor(panel.getSettings().colorEx);
                if(arg.length>0){
                    if(c.getAdditionalLabel()==null)c.setAdditionalLabel(arg[0]);
                    else c.setAdditionalLabel(c.getAdditionalLabel()+"->"+arg[0]);
                }
                panel.update(panel.getGraphics());
                Thread.sleep(panel.getSettings().t);
            }
        }
    }

    @Override
    public void visualEdge(int a,int b) throws InterruptedException {
        for (Edge c : G.getEdges()) {
            if ((c.getMySource().getNumb() == a && c.getMyTarget().getNumb() == b) || (c.getMyTarget().getNumb() == a && c.getMySource().getNumb() == b)) {
                c.setColor(panel.getSettings().colorEx);
                panel.update(panel.getGraphics());
                Thread.sleep(panel.getSettings().t);
            }
        }
    }

    @Override
    public void clearEdge(int a,int b){
        for (Edge c : G.getEdges()) {
            if ((c.getMySource().getNumb() == a && c.getMyTarget().getNumb() == b) || (c.getMyTarget().getNumb() == a && c.getMySource().getNumb() == b)) {
                c.setColor(panel.getSettings().ecolor);
                panel.update(panel.getGraphics());
            }
        }
    }
}
