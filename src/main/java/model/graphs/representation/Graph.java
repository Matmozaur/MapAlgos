package model.graphs.representation;

import model.elements.Edge;
import model.elements.Vertex;
import view.GraphPanel;
import java.util.LinkedList;
import java.util.List;

public abstract class Graph {

    final List<Vertex> vertexes=new LinkedList<>();
    final List<Edge> edges=new LinkedList<>();
    // --Commented out by Inspection (05.01.19 22:10):private GraphLogic logic;
    GraphPanel panel;

// --Commented out by Inspection START (05.01.19 22:10):
//    public GraphPanel getPanel() {
//        return panel;
//    }
// --Commented out by Inspection STOP (05.01.19 22:10)

    public List<Vertex> getVertexes() {
        return vertexes;
    }


    public List<Edge> getEdges() {
        return edges;
    }


// --Commented out by Inspection START (05.01.19 22:10):
//    public GraphLogic getLogic() {
//        return logic;
//    }
// --Commented out by Inspection STOP (05.01.19 22:10)


// --Commented out by Inspection START (05.01.19 22:10):
//    public void add(Vertex v){
//        vertexes.add(v);
//        logic.addV();
//    }
// --Commented out by Inspection STOP (05.01.19 22:10)

    void clear(){
        for (Vertex c : vertexes) {
            c.setColor(panel.getSettings().vcolor);
            c.setLabel(null);
        }
    }
}
