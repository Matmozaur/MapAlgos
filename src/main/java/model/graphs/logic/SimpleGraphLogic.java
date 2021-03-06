package model.graphs.logic;

import model.additional.Algos;
import controller.visualisation.painters.Visualable;
import model.graphs.representation.WeightGraph;


import java.util.LinkedList;
import java.util.Queue;

public class SimpleGraphLogic extends GraphLogic {

    final boolean[][] E;

    public SimpleGraphLogic(WeightGraph parent, int v, boolean[][] e) {
        super(parent,v);
        E=e;
    }

    public void addE(int a,int b){
        E[a][b] = E[b][a] = true;
    }

    void remove(int x) {
        for(int i=x;i<this.V;i++) {
            for(int j=0;j<this.V;j++) {
                this.E[i][j]=this.E[i+1][j];
                this.E[j][i]=this.E[j][i+1];
            }
        }
        this.V--;
    }

    public void remove(int a, int b) {
        E[a][b] = E[b][a] = false;
    }



    //Visualisation algorithms

    public void vDFS(int v, Visualable painter) throws InterruptedException{
        boolean[] Visited=new boolean[n];
        this.vDFS( v, Visited,painter);
    }

    private void vDFS( int v, boolean[] Visited,Visualable painter ) throws InterruptedException {
        Visited[v]=true;
        if(last>=0) painter.visualEdge(last,v);
        painter.visualVertex(v);
        for(int i=0;i<V;i++){
            last=v;
            if(E[v][i] && !Visited[i]){
                vDFS(i,Visited,painter);
            }
        }
    }

    public void vDeepSearch(Visualable painter) throws InterruptedException{
        boolean[] Visited = new boolean[n];
        for(int i=0;i<V;i++){
            if(!Visited[i]){
                last=-1;
                vDFS(i,Visited,painter);

            }
        }
    }

    public void vBFS(int v,Visualable painter) throws InterruptedException{
        last=-1;
        painter.visualVertex(v);
        boolean[] Visited=new boolean[n];
        Visited[v]=true;
        Queue<Integer> Q=new LinkedList<>();
        vBFS(v,Visited,Q,painter);
    }

    private void vBFS(int v, boolean[] Visited, Queue<Integer> Q, Visualable painter) throws InterruptedException{
        last=v;
        for(int i=0;i<V;i++){
            if(E[v][i] && !Visited[i]){
                Visited[i]=true;
                if(last>=0) painter.visualEdge(last,i);
                painter.visualVertex(i);
                Q.add(i);
            }
        }
        int x;
        while(!Q.isEmpty()){
            x=Q.remove();
            vBFS( x, Visited, Q, painter);
        }
    }

    public void vBreadthSearch(Visualable painter) throws InterruptedException{
        last=-1;
        boolean[] Visited = new boolean[n];
        Queue<Integer> Q=new LinkedList<>();
        for(int i=0;i<V;i++){
            if(!Visited[i]){
                Visited[i]=true;
                last=-1;
                painter.visualVertex(i);
                vBFS(i,Visited,Q,painter);
            }
        }


    }


    //pure graph algorithms

    /**
     *
     * @param v vertex
     * @return degree of vertex v
     */
    private int degreeOfVertex(int v){
        int d=0;
        for(int i=0;i<this.V;i++) {
            if(i==v) continue;
            if(this.E[v][i]) d++;
        }
        return d;
    }




    /**
     * DFS, returning number of vertex in sequence in DFS
     * @param v current vertex
     * @param numb current number in sequence
     * @param Sequence sequence of vertices
     * @param Visited table if visited
     * @return number in sequence
     */
    private int DFS(int v, int numb, int[] Sequence, boolean[] Visited){
        Visited[v]=true;
        Sequence[numb]=v;
        numb++;
        for(int i=0;i<this.V;i++){
            if(this.E[v][i] && !Visited[i]){
                numb=DFS(i,numb,Sequence,Visited);
            }
        }
        return numb;
    }


    /**
     * Checking if 2 vertices connected
     * @param v first vertex
     * @param u secound vertex
     * @return if connected
     */

    public boolean connected(int v, int u){
        int numb=0;
        int[] Sequence = new int[101];
        boolean[] Visited = new boolean[50];
        DFS(v,numb,Sequence,Visited);
        //DFS
        return Visited[u];
    }

    /**
     * Checking if graph is connected
     * @return if connected
     */
    private boolean connected() {
        for(int i=0;i<this.V-1;i++) {
            for(int j=i+1;j<this.V;j++) {
                if(!this.connected(i, j)) return false;
            }
        }
        return true;
    }


    @Override
    public String toString(){
        String s=""+V+"\n";
        for(int i=0;i<V;i++){
            for(int j=0;j<=i;j++){
                if(E[i][j]) System.out.print(i+"-"+j+"  ");
            }
        }
        return s;
    }

    /**
     * Get all informations about graph
     * @return info
     */
    public String basicInfo() {
        String info="";
        if(this.connected()) {
            info=info+"Connected \n";
        }
        else{
            info=info+"Unconnected \n";
        }
        info=info+"Order="+this.V+" \n";
        int size=0;
        for(int i=0;i<this.V;i++) {
            size=size+this.degreeOfVertex(i);
        }
        size=size/2;
        info=info+"Size="+size+" \n";
        int maxdim=0,mindim=this.V;
        for(int i=0;i<this.V;i++){
            if(this.degreeOfVertex(i)>maxdim) maxdim=this.degreeOfVertex(i);
            if(this.degreeOfVertex(i)<mindim) mindim=this.degreeOfVertex(i);
        }
        info=info+"Maximal degree="+maxdim+" \n";
        info=info+"Minimal degree="+mindim+" \n";

        return info;
    }


    private int eccentricyOfVertex(int v) {
        if(!this.connected()) return -1;
        boolean[] Visited = new boolean[n];
        int[] D = new int[n];
        D[v]=0;
        Queue<Integer> Q= new LinkedList<>();
        this.breadthDistance(v,Visited,D,Q);
        return Algos.max(D,this.V);
    }


    private void breadthDistance(int v, boolean[] Visited, int[] D, Queue<Integer> Q) {
        Visited[v]=true;
        for(int i=0;i<this.V;i++)
            if (this.E[v][i] && !Visited[i]) {
                Visited[i] = true;
                D[i] = D[v] + 1;
                Q.add(i);
            }
        int x;
        while(!Q.isEmpty()){
            x=Q.remove();
            this.breadthDistance(x,Visited,D,Q);
        }
    }

    public String vertexInfo(int v){
        String info="";
        info=info+"Degree="+degreeOfVertex(v)+" \n";
        if(eccentricyOfVertex(v)>=0) info+="Eccentricy="+eccentricyOfVertex(v)+" \n";
        return info;
    }

    public String allInfo(){
        String info=basicInfo();
        if(this.connected()) {
            int rad=V, diam=0;
            for(int i=0;i<this.V;i++){
                if(this.eccentricyOfVertex(i)>diam) diam=this.eccentricyOfVertex(i);
                if(this.eccentricyOfVertex(i)<rad) rad=this.eccentricyOfVertex(i);
            }
            info=info+"Diameter="+diam+" \n";
            info=info+"Radious="+rad+" \n";
        }
        return info;
    }
    private int[] diamAndRad(){
        if(this.connected()) {
            int rad=V, diam=0;
            for(int i=0;i<this.V;i++){
                if(this.eccentricyOfVertex(i)>diam) diam=this.eccentricyOfVertex(i);
                if(this.eccentricyOfVertex(i)<rad) rad=this.eccentricyOfVertex(i);
            }
            int[]A=new int[2];
            A[0]=diam;
            A[1]=rad;
            return A;
        }
        return null;
    }

    public int[] getCenter(){
        if(connected()) {
            int counter = 0;
            int[] A = new int[V];
            for (int i = 0; i < V; i++) {
                A[i] = -1;
            }
            for (int i = 0; i < V; i++) {
                if (eccentricyOfVertex(i) == diamAndRad()[1]) {
                    A[counter] = i;
                    counter++;
                }
            }
            return A;
        }
        return null;
    }

    public int[] getPeryfery(){
        if(connected()) {
            int counter = 0;
            int[] A = new int[V];
            for (int i = 0; i < V; i++) {
                A[i] = -1;
            }
            for (int i = 0; i < V; i++) {
                if (eccentricyOfVertex(i) == diamAndRad()[0]) {
                    A[counter] = i;
                    counter++;
                }
            }
            return A;
        }
        return null;
    }
}