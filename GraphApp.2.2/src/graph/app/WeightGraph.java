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
    public static SimpleGraph kraskal(WeightGraph G,GraphPanel panel) {
        for(int i=0;i<G.V;i++) {
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
                G.color(L[i][1], L[i][2] , panel);
                Counter--;
            }
            i++;
        }
        return T;
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


    public static void prim(GraphPanel panel, Vertex vStart) {
        LinkedList<Vertex> spanningTree = new LinkedList<>();
        LinkedList<Vertex> parents = new LinkedList<>();
        System.out.println(panel.G.V);

        int[] available = new int[panel.G.V];

        for(int i = 0; i < panel.G.V; i++) {
            available[i] = 0;
        }
        // av[v] = 0 <=> v nie sasiaduje z biezacym drzewem
        // av[v] = 1 <=> v sasiaduje z biezacym drzewem
        // av[v] = 2 <=> v nalezy do biezacego drzewa

        spanningTree.add(vStart);
        parents.add(vStart);
        available[vStart.getNumb()] = 2;

        Vertex vLast = vStart;  //ostatnio dodawany wierzchołek

        int iteration = 0;
        while(iteration < panel.G.V) {
            iteration += 1;
            for(Vertex v : panel.vertexes) {
                if(available[v.getNumb()] == 0 && panel.G.E[v.getNumb()][vLast.getNumb()] == true) {
                    available[v.getNumb()] = 1;
                    System.out.print(v.getNumb());
                    System.out.println(" is now available.");
                }
            }

            int minWeight = 99999;

            Vertex parent = vLast;
            Vertex candidate = vLast;
            for(Vertex v : panel.vertexes) {
                //System.out.println(v.getNumb());
                for(Vertex u: panel.vertexes) {
                    try {
                        if (available[v.getNumb()] == 1 && available[u.getNumb()] == 2) {
                            if (panel.G.E[u.getNumb()][v.getNumb()] == true) {
                                int weight = panel.G.W[u.getNumb()][v.getNumb()];
                                if (weight < minWeight) {
                                    System.out.println("relaxation");
                                    minWeight = weight;
                                    candidate = v;
                                    parent = u;
                                }
                            }
                        }
                    } catch (NullPointerException e) {
                        System.out.println("error");
                    }
                }
            }
            if(candidate == vLast) {
                continue;
            }
            if(candidate != null) {
                //System.out.print("Candidate chosen: its weight ");
                //System.out.println(minWeight);
                spanningTree.add(candidate);
                parents.add(parent);
                available[candidate.getNumb()] = 2;
                vLast = candidate;
            } else {
                //System.out.println("Candidate not found");
                //???
            }
        }
        animatePrim(spanningTree, parents, panel);
    }

    public static void animatePrim(LinkedList<Vertex> spanningTree, LinkedList<Vertex> parents, GraphPanel panel) {
        int index = 0;
        for (Vertex v : spanningTree) {
            algos.sleep(t);
            panel.G.color(parents.get(index).getNumb(), v.getNumb(), panel);
            index++;
            algos.sleep(t);
            panel.G.color(v.getNumb(), panel);
        }
    }
}
