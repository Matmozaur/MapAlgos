package model;

import controller.visualisation.painters.Visualable;
import model.graphs.logic.SimpleGraphLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class SimpleGraphLogicTest {
    private static SimpleGraphLogic G;
    private static final List<Integer> vList=new LinkedList<>();
    private static final List<Integer[]> eList=new LinkedList<>();
    private static SequancePainter sPainter;


    @BeforeAll
    static void prepareGraphLogic(){
        G=new SimpleGraphLogic(null,5,new boolean[][] {{true,true,true,false,false},{true,true,true,false,false},
                {true,true,true,true,false},{false,false,true,true,true},{false,false,false,true,true}});
        sPainter=new SequancePainter();
    }

    @Test
    void dfsTestNormal(){
        try {
            G.vDFS(3,sPainter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Integer> ExpectedV=new LinkedList<>();
        ExpectedV.add(3);
        ExpectedV.add(2);
        ExpectedV.add(0);
        ExpectedV.add(1);
        ExpectedV.add(4);

        Assertions.assertIterableEquals(ExpectedV,vList);
        sPainter.clear();
    }

    @Test
    void infoTestNormal(){
        Assertions.assertEquals("Connected \n"+"Order="+5+" \n"+ "Size="+5+" \n"+
                "Maximal degree="+3+" \n"+"Minimal degree="+1+" \n",G.basicInfo());
    }

    @Test
    void bfsTestNormal(){
        try {
            G.vBFS(3,sPainter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Integer> ExpectedV=new LinkedList<>();
        ExpectedV.add(3);
        ExpectedV.add(2);
        ExpectedV.add(4);
        ExpectedV.add(0);
        ExpectedV.add(1);

        Assertions.assertIterableEquals(ExpectedV,vList);
        sPainter.clear();
    }

    @Test
    void centerTested(){
        int[] Actual=G.getCenter();
        int[] Expected=new int[] {2,3,-1,-1,-1};
        Assertions.assertArrayEquals(Expected,Actual);
    }

    @Test
    void peryferyTested(){
        int[] Actual=G.getPeryfery();
        int[] Expected=new int[] {0,1,4,-1,-1};
        Assertions.assertArrayEquals(Expected,Actual);
    }

    @Test
    void connectedTestedTrue(){
        Assertions.assertTrue(G.connected(0, 4));
    }

    @Test
    void connectedTestedFalse(){
        G.remove(3,4);
        Assertions.assertFalse(G.connected(0, 4));
        G.addE(3,4);
    }





    private static class SequancePainter implements Visualable{
        @Override
        public void visualVertex(int x,String... args){
            vList.add(x);
        }

        @Override
        public void visualEdge(int x,int y){
            eList.add(new Integer[] {x,y});
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
