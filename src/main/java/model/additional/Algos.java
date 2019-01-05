package model.additional;

public abstract class Algos {


    public static int max(int[] D, int n) {
        int m=D[0];
        for(int i=1;i<n;i++) if(D[i]>m) m=D[i];
        return m;
    }


// --Commented out by Inspection START (05.01.19 22:10):
//    /**
//     * swaps 2 elements
//     *
//     * @param A first
//     * @param B second
//     */
//    public static void swap(int[] A, int[] B) {
//        int[] C = A;
//        A = B;
//        B = C;
//    }
// --Commented out by Inspection STOP (05.01.19 22:10)

    public static int[][] sortEdges(int V,boolean[][] E,int[][] W){
        int[][] L = new int[V * V][3];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (!E[i][j] ||i==j) L[V * i + j][0] = 99999;
                else {
                    L[V * i + j][0] = W[i][j];
                    L[V * i + j][1] = i;
                    L[V * i + j][2] = j;
                }
            }
        }
        for (int i = 0; i < V * V; i++) {
            for (int j = 0; j < V * V-1; j++) {
                if (L[j][0] > L[j+1][0]) {
                    int[] A = new int[3];
                    A[0]=L[j][0];
                    A[1]=L[j][1];
                    A[2]=L[j][2];
                    L[j][0]=L[j+1][0];
                    L[j][1]=L[j+1][1];
                    L[j][2]=L[j+1][2];
                    L[j+1][0]=A[0];
                    L[j+1][1]=A[1];
                    L[j+1][2]=A[2];

                }
            }
        }
        return L;
    }

}
