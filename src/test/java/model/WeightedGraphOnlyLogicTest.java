package model;

import controller.visualisation.painters.Visualable;
import model.graphs.logic.WeightGraphLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class WeightedGraphOnlyLogicTest {
    private static WeightGraphLogic G;
    private static final List<Integer> vList=new LinkedList<>();
    private static final List<int[]> eList=new LinkedList<>();
    private static SequancePainter sPainter;


    @BeforeAll
    static void prepareGraphLogic(){
        G=new WeightGraphLogic(null,5,new boolean[][] {{true,true,true,false,false},{true,true,true,false,false},
                {true,true,true,true,false},{false,false,true,true,true},{false,false,false,true,true}},
                new int[][] {{0,2,2,0,0},{2,0,5,0,0},{2,5,0,1,0},{0,0,1,0,7},{0,0,0,7,0}});
        sPainter=new SequancePainter();
    }

    @Test
    void kraskalTest(){
        try {
            G.vkraskal(sPainter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<int[]> ExpectedE=new LinkedList<>();
        ExpectedE.add(new int[] {2,3});
        ExpectedE.add(new int[] {0,1});
        ExpectedE.add(new int[] {0,2});
        ExpectedE.add(new int[] {3,4});

        for(int i=0;i<4;i++){
            System.out.println(eList.get(i)[0]+"  "+eList.get(i)[1]);
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
