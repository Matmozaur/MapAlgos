package model;

public class PatchSet {
    int length;
    int sum;
    int P[][]=new int[Graph.n][Graph.n];
    public PatchSet() {
        for(int i=0;i<Graph.n;i++) {
            for(int j=0;j<Graph.n;j++) {
                P[i][j]=-1;
            }
        }
    }
}