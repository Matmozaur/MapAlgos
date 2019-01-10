package model;

import model.elements.Edge;
import model.elements.Vertex;
import model.elements.WeightEdge;
import model.graphs.representation.WeightGraph;
import model.settings.Settings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class GraphConstructionTest {
    private static List<Vertex> vList=new LinkedList<>();
    private static List<WeightEdge> eList=new LinkedList<>();
    private static Settings settings=new Settings();

    @BeforeAll
    static void prepareMock(){
        vList.add(new Vertex(10,10,0,null,settings));
        vList.add(new Vertex(40,40,1,"x1",settings));
        vList.add(new Vertex(30,30,2,null,settings));
        vList.add(new Vertex(20,20,3,null,settings));
        eList.add(new WeightEdge(vList.get(0),vList.get(1),5,settings));
        eList.add(new WeightEdge(vList.get(2),vList.get(1),10,settings));
        eList.add(new WeightEdge(vList.get(3),vList.get(1),15,settings));
    }

    @Test
    void createGraphTest(){
        WeightGraph G=prepareG();

        Assertions.assertIterableEquals(vList,G.getVertexes());
        Assertions.assertIterableEquals(eList,G.getEdges());
        Assertions.assertEquals(4,G.getLogic().getV());
    }

    @Test
    void removeEdgeTest(){
        WeightGraph G=prepareG();
        G.removeE(new Edge(vList.get(1),vList.get(3),settings));
        eList.remove(2);

        Assertions.assertIterableEquals(eList,G.getEdges());
        refreshMock();
    }

    @Test
    void removeVertexTest(){
        WeightGraph G=prepareG();
        G.remove(vList.get(3));
        eList.remove(2);
        vList.remove(3);

        Assertions.assertIterableEquals(eList,G.getEdges());
        Assertions.assertIterableEquals(vList,G.getVertexes());
        refreshMock();
    }

    private WeightGraph prepareG(){
        WeightGraph G=new WeightGraph();
        Vertex v1=new Vertex(10,10,0,null,settings);
        Vertex v2=new Vertex(40,40,1,"x1",settings);
        Vertex v3=new Vertex(30,30,2,null,settings);
        Vertex v4=new Vertex(20,20,3,null,settings);
        G.add(v1);
        G.add(v2);
        G.add(v3);
        G.add(v4);
        G.add(new WeightEdge(v1,v2,5,settings));
        G.add(new WeightEdge(v3,v2,10,settings));
        G.add(new WeightEdge(v2,v4,15,settings));
        return G;
    }
    private static void refreshMock(){
        vList.clear();
        eList.clear();
        vList.add(new Vertex(10,10,0,null,settings));
        vList.add(new Vertex(40,40,1,"x1",settings));
        vList.add(new Vertex(30,30,2,null,settings));
        vList.add(new Vertex(20,20,3,null,settings));
        eList.add(new WeightEdge(vList.get(0),vList.get(1),5,settings));
        eList.add(new WeightEdge(vList.get(2),vList.get(1),10,settings));
        eList.add(new WeightEdge(vList.get(3),vList.get(1),15,settings));
    }

}
