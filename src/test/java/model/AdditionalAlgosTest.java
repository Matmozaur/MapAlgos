package model;

import model.additional.Algos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AdditionalAlgosTest {



    @Test
    void coorsTest1Q(){
        int[] A= Algos.coors(200,200,30);
        Assertions.assertEquals(80,A[0]);
        Assertions.assertEquals(50,A[1]);
    }

    @Test
    void coorsTest3Q(){
        int[] A= Algos.coors(300,400,600);
        Assertions.assertEquals(150,A[0]);
        Assertions.assertEquals(350,A[1]);
    }

    @Test
    void sortEdgesTest(){
        boolean[][]E=new boolean[][] {{true,false,true},{false,true,true},{true,true,true}};
        int[][]W=new int[][] {{0,0,3},{0,0,5},{2,1,0}};
        int[][]A=Algos.sortEdges(3,E,W);
        Assertions.assertArrayEquals(new int[]{1,2,1},A[0]);
    }
}
