package model.additional;

public interface Algos {


    static int max(int[] D, int n) {
        int m=D[0];
        for(int i=1;i<n;i++) if(D[i]>m) m=D[i];
        return m;
    }



    static int[][] sortEdges(int V,boolean[][] E,int[][] W){
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

    static int[] coors(int w,int h,int n) {
        int[] A=new int[2];
        if(n<=w-100){
            A[0]=n+50;
            A[1]=50;
        }
        if(n>w-100&&n<=w+h-200){
            A[0]=w-50;
            A[1]=n-w+150;
        }
        if(n>w+h-200&&n<=2*w+h-300){
            A[0]=w-50-(n-(w+h-200));
            A[1]=h-50;
        }
        if(n>2*w+h-300){
            A[0]=50;
            A[1]=h-50-(n-(2*w+h-300));
        }
        return A;
    }



}
