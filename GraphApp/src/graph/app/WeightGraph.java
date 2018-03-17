package graph.app;

public class WeightGraph extends SimpleGraph {
    int W[][] = new int[n][n];

    public WeightGraph(int v, boolean[][] e, int[][] w) {
        super(v, e);
        W = w;
    }

    public void remove(int x) {
    	super.remove(x);
		for(int i=x;i<this.V;i++) {
			for(int j=0;j<this.V;j++) {
				this.W[i][j]=this.W[i+1][j];
				this.W[j][i]=this.W[j][i+1];
			}
		}
		this.V--;
	}

    //Pure graphs
    public static SimpleGraph kraskal(WeightGraph G) {
        SimpleGraph T = new SimpleGraph(G.V, new boolean[n][n]);
        SimpleGraph U = new SimpleGraph(G.V, new boolean[n][n]);
        int Counter = G.V - 1;
        //Making and sorting list of edges
        int L[][] = new int[n * n / 2][3];
        for (int i = 0; i < G.V; i++) {
            for (int j = 0; j <= i; j++) {
                if (G.E[i][j] == false) L[G.V * i + j][0] = 99999;
                else {
                    L[G.V * i + j][0] = G.W[i][j];
                    L[G.V * i + j][1] = i;
                    L[G.V * i + j][2] = j;
                }
            }
        }
        //using bubble sort
        for (int i = 0; i < G.V * (G.V - 1) / 2; i++) {
            for (int j = i + 1; j < G.V * (G.V - 1) / 2; j++) {
                //only checking the 3rd column
                if (L[i][0] > L[j][0])
                    swap(L[i], L[j]);
            }
        }
        //End of sorting
        int i = 0;
        while (Counter > 0 && i < n * n / 2) {
            if (!Connected(T, L[i][1], L[i][2])) {
                U.E[L[i][1]][L[i][2]] = U.E[L[i][2]][L[i][1]] = true;
                T.E[L[i][1]][L[i][2]] = T.E[L[i][2]][L[i][1]] = true;
                Counter--;
            }
            i++;
        }
        return T;
    }


    public static SimpleGraph prim(WeightGraph G) {
        SimpleGraph T = new SimpleGraph(G.V, new boolean[n][n]);
        int P[] = new int[n], Q[] = new int[50], Visited[] = new int[n];
        for (int i = 0; i < G.V; i++) {
            P[i] = 999;
            Q[i] = -1;
        }
        P[0] = 0;

        return T;
    }
}
