package model;

import model.elements.Vertex;
import model.elements.WeightEdge;
import model.graphs.representation.WeightGraph;
import model.settings.Settings;
import org.junit.jupiter.api.BeforeAll;

public class WeightedGraphLogicWithParentTest {
    private static WeightGraph G;

    @BeforeAll
    void prepare(){
        Settings settings=new Settings();
        G=new WeightGraph();
        Vertex v0=new Vertex(10,10,0,null,settings);
        G.add(v0);
        Vertex v1=new Vertex(20,20,1,null,settings);
        G.add(v1);
        Vertex v2=new Vertex(30,30,2,null,settings);
        G.add(v2);
        Vertex v3=new Vertex(40,40,3,null,settings);
        G.add(v3);
        Vertex v4=new Vertex(50,50,4,null,settings);
        G.add(v4);
        G.add(new WeightEdge(v0,v1,2,settings));
        G.add(new WeightEdge(v0,v2,4,settings));
        G.add(new WeightEdge(v2,v1,3,settings));
        G.add(new WeightEdge(v2,v3,2,settings));
        G.add(new WeightEdge(v3,v4,7,settings));
    }



}
