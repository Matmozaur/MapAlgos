package graph.app;

public class WeightGraph extends SimpleGraph {
    int W[][] = new int[n][n];

    public WeightGraph(int v, boolean[][] e, int[][] w) {
        super(v, e);
        W = w;
    }

    public void remove(int x) {
        super.remove(x);
        for (int i = x; i < this.V; i++) {
            for (int j = 0; j < this.V; j++) {
                this.W[i][j] = this.W[i + 1][j];
                this.W[j][i] = this.W[j][i + 1];
            }
        }
        this.V--;
    }

    public static SimpleGraph kraskal(WeightGraph G, GraphPanel panel) {
        for (int i = 0; i < G.V; i++) {
            G.color(i, panel);
        }
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
                algos.sleep(G.t);
                G.color(L[i][1], L[i][2], panel);
                Counter--;
            }
            i++;
        }
        return T;
    }
/*
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
*/
    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    private static int minKey(int V, int key[], Boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min)
            {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }
/*
Tutaj jeszcze sporo do zrobienia....
    public static SimpleGraph prim(WeightGraph G, Vertex start, GraphPanel panel) {
        SimpleGraph T = new SimpleGraph(G.V, new boolean[n][n]);

        // Array to store constructed MST
        int parent[] = new int[G.V];

        // Key values used to pick minimum weight edge in cut
        int key[] = new int [G.V];

        // To represent set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[G.V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < G.V; i++)
        {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0;     // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < G.V - 1; count++)
        {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(G.V, key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < G.V; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (G.E[u][v]!= false && mstSet[v] == false &&
                        G.W[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = G.W[u][v];
                }
        }
        int counter = 1;
        while(counter < G.V - 1) {
            T.E[counter][parent[counter]] = true;
            T.E[parent[counter]][counter] = true;
            G.color(parent[counter++], panel);
            algos.sleep(G.t);
        }
        return T;
    }
    */
}
