package graphs.pure;


public class directedGraph extends WeightGraph {
    // We consider directed graphs defined on the weighted graphs

    int orientations[][] = new int[n][n];

    public directedGraph(int v, boolean e[][], int weights[][], int orientations[][]) {
        // e[][] array informs if we have any edge in the graph G,
        // in orientations[][] array we can find info about edge orientations
        super(v, e, weights);
        this.orientations = orientations;
    }

    public static int[] dijkstra(directedGraph G, int start) {
        // weights cannot be negative
        for (int i = 0; i < G.V; i++) {
            for (int j = 0; j < G.V; j++) {
                if (G.W[i][j] < 0) {
                    // moze jakis wyjatek??
                    // raise error?
                    return null;
                }
            }
        }
        int[] distances = new int[G.V];
        int[] prev = new int[G.V];
        boolean[] visited = new boolean[G.V];
        final int sup = 999;
        for (int i = 0; i < G.V; i++) {
            distances[i] = sup;
            prev[i] = -1;
            visited[i] = false;
        }
        distances[start] = 0;
        visited[start] = true;

        while (!allVisited(visited, G.V)) {
            int u = findMin(visited, distances, G.V);
            visited[u] = true;
            // relax
            for(int i = 0; i < G.V; i++) {
                if(visited[i] == false && G.orientations[u][i] == 1 && distances[i] > distances[u] + G.W[u][i]) {
                    distances[i] = distances[u] + G.W[u][i];
                    prev[i] = u;
                }
            }
        }
        // We return an array which includes predecessors of every vertex on the "cheapest" path
        // Its cost can be computed again while reading parent[] or returned here somehow xd
        return prev;
    }

    private static boolean allVisited(boolean[] visited, int n) {
        for(int i = 0; i < n; i++) {
            if(visited[i] == false) {
                return false;
            }
        }
        return true;
    }

    private static int findMin(boolean[] visited, int[] distances, int n) {
        int minIndex = 0;
        int min = 999;
        for(int i = 0; i < n; i++) {
            if(visited[i] == false && min > distances[i]) {
                minIndex = i;
                min = distances[i];
            }
        }
        return minIndex;
    }
}
