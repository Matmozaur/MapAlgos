package model.graphs.representation;

import model.elements.Edge;
import model.elements.Vertex;

import java.util.LinkedList;
import java.util.List;

public class Subgraph {
    final List<Vertex> vertexes=new LinkedList<>();
    final List<Edge> edges=new LinkedList<>();

    public List<Vertex> getVertexes() {
        return vertexes;
    }
    public List<Edge> getEdges() {
        return edges;
    }
}
