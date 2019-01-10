package model;

import controller.visualisation.painters.Visualable;
import model.graphs.logic.SimpleGraphLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class simpleGraphLogicTest {
    private static SimpleGraphLogic G;
    private static List<Integer> vList=new LinkedList<>();
    private static List<Integer[]> eList=new LinkedList<>();
    private static SequancePainter sPainter;


    @BeforeAll
    static void prepareGraphLogic(){
        G=new SimpleGraphLogic(null,5,new boolean[][] {{true,true,true,false,false},{true,true,true,false,false},
                {true,true,true,true,false},{false,false,true,true,true},{false,false,false,true,true}});
        sPainter=new SequancePainter();
    }

    @Test
    void dfsTest(){
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
    void infoTest(){
        Assertions.assertEquals("Connected \n"+"Order="+5+" \n"+ "Size="+5+" \n"+
                "Maximal degree="+3+" \n"+"Minimal degree="+1+" \n",G.basicInfo());
    }

    @Test
    void bfsTest(){
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






    private static class SequancePainter implements Visualable{
        @Override
        public void visualVertex(int x,String... args){
            vList.add(x);
        }

        @Override
        public void visualEdge(int x,int y){
            eList.add(new Integer[] {x,y});
        }

        void clear(){
            vList.clear();
            eList.clear();
        }
    }

}
