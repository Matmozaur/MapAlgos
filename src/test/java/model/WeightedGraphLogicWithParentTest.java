package model;

import controller.visualisation.painters.Visualable;
import model.elements.Vertex;
import model.elements.WeightEdge;
import model.graphs.representation.WeightGraph;
import model.settings.Settings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class WeightedGraphLogicWithParentTest {
    private static WeightGraph G;
    private static final List<Integer> vList=new LinkedList<>();
    private static final List<int[]> eList=new LinkedList<>();
    private static SequancePainter sPainter;

    @BeforeAll
    static void prepare(){
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
        sPainter=new SequancePainter();
    }

    @Test
    void primTest(){
        try {
            G.getLogic().vprim(sPainter,3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<int[]> ExpectedE=new LinkedList<>();
        ExpectedE.add(new int[] {3,2});
        ExpectedE.add(new int[] {2,1});
        ExpectedE.add(new int[] {1,0});
        ExpectedE.add(new int[] {3,4});

        for(int i=0;i<4;i++){
            Assertions.assertArrayEquals(ExpectedE.get(i),eList.get(i));
        }
        sPainter.clear();
    }

    @Test
    void dijkstraTest(){
        try {
            G.getLogic().vdijkstra(3,sPainter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<int[]> ExpectedE=new LinkedList<>();
        ExpectedE.add(new int[] {2,3});
        ExpectedE.add(new int[] {4,3});
        ExpectedE.add(new int[] {0,2});
        ExpectedE.add(new int[] {1,2});

        for(int i=0;i<4;i++){
            //System.out.println(eList.get(i)[0]+"  "+eList.get(i)[1]);
            Assertions.assertArrayEquals(ExpectedE.get(i),eList.get(i));
        }
        sPainter.clear();
    }





    private static class SequancePainter implements Visualable {
        @Override
        public void visualVertex(int x,String... args){
            vList.add(x);
        }

        @Override
        public void visualEdge(int x,int y){
            eList.add(new int[] {x,y});
        }

        @Override
        public void clearEdge(int x,int y){
            eList.remove(new Integer[] {x,y});
        }

        void clear(){
            vList.clear();
            eList.clear();
        }
    }
}
