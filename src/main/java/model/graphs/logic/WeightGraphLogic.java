package model.graphs.logic;

import model.additional.Algos;
import controller.visualisation.Visualable;
import model.elements.Vertex;
import model.graphs.representation.Graph;

import java.util.LinkedList;


public class WeightGraphLogic extends SimpleGraphLogic {
    private final int[][] W ;

    public WeightGraphLogic(Graph parent, int v, boolean[][] e, int[][] w) {
        super(parent, v, e);
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

    public void addE(int a,int b,int w){
       super.addE(a,b);
       W[a][b] = W[b][a] = w;
    }

    public  void remove(int a,int b){
        super.remove(a,b);
        W[a][b]=W[b][a]=0;
    }



    public SimpleGraphLogic vkraskal(Visualable painter) throws InterruptedException {
        for(int i=0;i<V;i++) {
            painter.visualVertex(i);
        }
        SimpleGraphLogic T = new SimpleGraphLogic(this.parent,V, new boolean[n][n]);
        SimpleGraphLogic U = new SimpleGraphLogic(this.parent,V, new boolean[n][n]);
        int Counter = V- 1;
        //Making and sorted list of edges
        int[][] L = Algos.sortEdges(V, E, W);
        int i = 0;
        while (Counter > 0 && i < n * n / 2) {
            if (T.Connected(L[i][1], L[i][2])) {
                U.E[L[i][1]][L[i][2]] = U.E[L[i][2]][L[i][1]] = true;
                T.E[L[i][1]][L[i][2]] = T.E[L[i][2]][L[i][1]] = true;
                painter.visualEdge(L[i][1],L[i][2]);
                Counter--;
            }
            i++;
        }
        return T;
    }

    public void vprim(Visualable painter, Vertex vStart) throws InterruptedException {
        LinkedList<Vertex> spanningTree = new LinkedList<>();
        LinkedList<Vertex> parents = new LinkedList<>();
        int[] available = new int[V];
        for (int i = 0; i < V; i++) {
            available[i] = 0;
        }
        spanningTree.add(vStart);
        parents.add(vStart);
        available[vStart.getNumb()] = 2;
        Vertex vLast = vStart;
        int iteration = 0;
        while (iteration < V) {
            iteration += 1;
            for (Vertex v : parent.getVertexes()) {
                if (available[v.getNumb()] == 0 && E[v.getNumb()][vLast.getNumb()]) {
                    available[v.getNumb()] = 1;
                }
            }
            int minWeight = Integer.MAX_VALUE;
            Vertex parentv = vLast;
            Vertex candidate = vLast;
            for (Vertex v : parent.getVertexes()) {
                for (Vertex u : parent.getVertexes()) {
                    if (available[v.getNumb()] == 1 && available[u.getNumb()] == 2) {
                        if (E[u.getNumb()][v.getNumb()]) {
                            int weight = W[u.getNumb()][v.getNumb()];
                            if (weight < minWeight) {
                                minWeight = weight;
                                candidate = v;
                                parentv = u;
                            }
                        }
                    }
                }
            }
            if (candidate == vLast) {
                continue;
            }
            spanningTree.add(candidate);
            parents.add(parentv);
            available[candidate.getNumb()] = 2;
            vLast = candidate;
        }
        int index = 0;
        for (Vertex v : spanningTree) {
            painter.visualEdge(parents.get(index).getNumb(),v.getNumb());
            index++;
            painter.visualVertex(v.getNumb());
        }
    }

    public void dijkstra(Vertex vStart,Visualable painter) throws InterruptedException {
        int[] distances = new int[V];
        int[] available = new int[V];
        int[] parentv = new int[V];
        int sup = 999999;
        for (int i = 0; i <V; i++) {
            distances[i] = sup;
            available[i] = 0;
        }
        distances[vStart.getNumb()] = 0;
        available[vStart.getNumb()] = 2;
        parentv[vStart.getNumb()] = -1;
        Vertex vLast = vStart;
        int iteration = 0;
        while (iteration < V) {
            iteration += 1;
            for (Vertex v : parent.getVertexes() ) {
                if (available[v.getNumb()] == 0 && E[v.getNumb()][vLast.getNumb()]) {
                    available[v.getNumb()] = 1;
                }
            }

            int min = 99999;
            for(Vertex v : parent.getVertexes()) {
                if(available[v.getNumb()] == 1) {
                    if(distances[v.getNumb()] < min) {
                        min = distances[v.getNumb()];
                        vLast = v;
                    }
                }
            }
            available[vLast.getNumb()] = 2;

            // relaxation
            for(Vertex v : parent.getVertexes()) {
                if(E[v.getNumb()][vLast.getNumb()]) {
                    int weight = W[v.getNumb()][vLast.getNumb()];
                    if(distances[v.getNumb()] > distances[vLast.getNumb()] + weight) {
                        distances[v.getNumb()] = distances[vLast.getNumb()] + weight;
                        parentv[v.getNumb()] = vLast.getNumb();
                    }
                }
            }
        }
        for(Vertex v:parent.getVertexes()) {
            int i=v.getNumb();
            v.setLabel(v.getLabel()+distances[i]);
            painter.visualVertex(i);
            painter.visualEdge(i,parentv[i]);
        }

    }
}