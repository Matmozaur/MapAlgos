package graph.app;
import java.util.Queue;

import graphs.pure.simpleGraph;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;

public class SimpleGraph extends Graph {
	
	boolean E[][]=new boolean[n][n];
	public SimpleGraph(int v,boolean e[][]) {
		super(v);
		E=e;
	}
	
	
	
	
	
	/*
	 * Panel algorithms
	 */
	
	
	
	public void remove(int x) {
		for(int i=x;i<this.V;i++) {
			for(int j=0;j<this.V;j++) {
				this.E[i][j]=this.E[i+1][j];
				this.E[j][i]=this.E[j][i+1];
			}
		}
		this.V--;
	}
	
	//Search algorithms
	
	public void DFS(SimpleGraph G,int v,GraphPanel panel) {
		boolean Visited[]=new boolean[n];
		G.DFS(G, v, Visited, panel);
	}
	public void DFS(SimpleGraph G,int v,boolean Visited[],GraphPanel panel){
		Visited[v]=true;
		algos.sleep(t);
		if(last>=0) color(last,v,panel);
		algos.sleep(t);
		color(v,panel);
		for(int i=0;i<G.V;i++){
			last=v;
			if(G.E[v][i]==true && Visited[i]==false){
				DFS(G,i,Visited,panel);
			}
		}
	}
	
	private int DFS(SimpleGraph G,int v,int numb,int Sequence[],boolean Visited[],GraphPanel panel){
		Visited[v]=true;
		algos.sleep(500);
		if(last>=0) color(last,v,panel);
		algos.sleep(500);
		color(v,panel);
		Sequence[numb]=v;
		numb++;
		for(int i=0;i<G.V;i++){
			last=v;
			if(G.E[v][i]==true && Visited[i]==false){
				numb=DFS(G,i,numb,Sequence,Visited,panel);
			}
		}
		return numb;
	}
	public  void DeepSearch(SimpleGraph G,GraphPanel panel){
		int numb=0;
		int Sequence[]= new int[2*n+1];
		boolean Visited[]=new boolean[n];
		for(int i=0;i<G.V;i++){		
			if(Visited[i]==false){
				last=-1;
				numb=DFS(G,i,numb,Sequence,Visited,panel);
				Sequence[numb]=-1;
				numb++;
			}
		}
	}
	
	
	public  void BFS(SimpleGraph G,int v,GraphPanel panel){
		color(v,panel);
		boolean Visited[]=new boolean[n];
		Visited[v]=true;
		Queue<Integer> Q=new LinkedList<Integer>();
		BFS(G,v,Visited,Q,panel);
	}
	
	private  void BFS(SimpleGraph G,int v,boolean Visited[],Queue<Integer> Q,GraphPanel panel){
		last=v;
		for(int i=0;i<G.V;i++){
			if(G.E[v][i]==true && Visited[i]==false){
				Visited[i]=true;
				algos.sleep(500);
				if(last>=0) color(last,i,panel);
				algos.sleep(500);
				color(i,panel);
				Q.add(i);
			}
		}
		int x;
		while(!Q.isEmpty()){
			x=Q.remove();
			BFS( G, x, Visited, Q, panel);
		}
	}
	
	public  void BreadthSearch(SimpleGraph G,GraphPanel panel){
		boolean Visited[]=new boolean[n];
		Queue<Integer> Q=new LinkedList<Integer>();
		for(int i=0;i<G.V;i++){		
			if(Visited[i]==false){
				Visited[i]=true;
				last=-1;
				color(i,panel);
				BFS(G,i,Visited,Q,panel);
			}
		}

	
	}
	
	//Elements of graphs
	
	public void colorPatch(int P[],int k,GraphPanel panel){
		for(int i=0;i<=k;i++) {
			color(i,panel);
			if(i>0&&this.E[i][i-1]) color(i,i-1,panel);
		}
	}
	

	public void graphCenter(GraphPanel panel) {
		int E[]=new int[n];
		for(int i=0;i<this.V;i++) {
			E[i]=eccentricyOfVertex(i);
		}
		int m=algos.min(E, this.V);
		int Sub[]=new int[n];
		int k=0;
		for(int i=0;i<this.V;i++) {
			if(E[i]==m) {
				Sub[k]=i;
				k++;
			}
		}
		this.colorInducedSubgraph(k,Sub,panel);
	}
	public void graphPeryfery(GraphPanel panel) {
		int E[]=new int[n];
		for(int i=0;i<this.V;i++) {
			E[i]=eccentricyOfVertex(i);
		}
		int m=algos.max(E, this.V);
		int Sub[]=new int[n];
		int k=0;
		for(int i=0;i<this.V;i++) {
			if(E[i]==m) {
				Sub[k]=i;
				k++;
			}
		}
		this.colorInducedSubgraph(k,Sub,panel);
	}
	
	public void colorInducedSubgraph(int k,int Sub[], GraphPanel panel) {
		for(int i=0;i<k;i++) {
			color(Sub[i],panel);
			for(int j=i+1;j<k;j++) {
				if(this.E[Sub[i]][Sub[j]]) color(Sub[i],Sub[j],panel);
			}
		}
	}
	
	public void colorPatch(int P[],GraphPanel panel) {
		for(int i=0;i<n;i++) {
			if(P[i]>=0) {
				if(i>0) {
					color(P[i],P[i-1],panel);
					algos.sleep(t);
				}
				color(P[i],panel);
				algos.sleep(t);
			}
		}
	}
	
	/*
	 * Pure graphs algorithms
	 */
	public int[] shortestPatch(int v,int u){
		if(!this.Connected(this, v, u)) return null;
		int P[]=new int[n];
		for(int i=0;i<n;i++) {
			P[i]=-1;
		}
		int Last[]=new int[n];
		Last[v]=-1;
		boolean Visited[]=new boolean[n];
		Visited[v]=true;
		BPatch(v,Last,Visited);
		int i=u,j=0;
		do {
			P[j]=i;
			i=Last[i];
			j++;
			
		}
		while(i>=0);
		return P;
	}
	
	public void BPatch(int v, int Last[], boolean Visited[]){
		Queue<Integer> Q=new LinkedList<Integer>();
		for(int i=0;i<V;i++){
			if(E[v][i]==true && Visited[i]==false){
				Visited[i]=true;
				Q.add(i);
				Last[i]=v;
			}
		}
		int x;
		while(!Q.isEmpty()){
			x=Q.remove();
			BPatch(x,Last,Visited);
			}
	}
	
	
	public PatchSet shortestPatches(int v,int u){
		if(!this.Connected(this, v, u)) return null;
		PatchSet P=new PatchSet();
		/*
		int D[]=new int[n];
		boolean Visited[]=new boolean[n];
		breadthDistance(v,Visited,D);
		int k=D[u];
		P.length=k;
		P.sum++;
		P.P[0][0]=u;
		for(int i=1;i<=k;i++) {
			for(int j=0;j<n;j++) {
				if(D[j]==k-i) {
					for(int l=0;l<P.sum;l++) {
						if(E[j][P.P[l][i-1]]) {
							if(P.P[l][i]<0) {
								P.P[l][i]=j;
							}
							else {
								P.sum++;
							}
						}
					}
				}
			}
		}
		*/
		return P;
	}
	
	
	public int degreeOfVertex(int v){
		int d=0;
		for(int i=0;i<this.V;i++) {
			if(this.E[v][i]==true) d++;
		}
		return d;
	}
	
	public int eccentricyOfVertex(int v) {
		if(!this.Connected(this)) return -1;
		boolean Visited[]=new boolean[n];
		int D[]=new int[n];
		D[v]=0;
		breadthDistance(v,Visited,D);
		return algos.max(D,this.V);
	}
	
	public void breadthDistance(int v,boolean Visited[],int D[]) {
		Visited[v]=true;
		Queue<Integer> Q=new LinkedList<Integer>();
		for(int i=0;i<this.V;i++){
			if(this.E[v][i]==true && Visited[i]==false){
				Visited[i]=true;
				D[i]=D[v]+1;
				Q.add(i);
			}
		}
		int x;
		while(!Q.isEmpty()){
			x=Q.remove();
			this.breadthDistance(x,Visited,D);
		}
	}
	
	//Search algorithms
	private static int DFS(SimpleGraph G,int v,int numb,int Sequence[],boolean Visited[]){
		Visited[v]=true;
		Sequence[numb]=v;
		numb++;
		for(int i=0;i<G.V;i++){
			if(G.E[v][i]==true && Visited[i]==false){
				numb=DFS(G,i,numb,Sequence,Visited);
			}
		}
		return numb;
	}
	public static int[] DeepSearch(SimpleGraph G){
		int numb=0;
		int Sequence[]= new int[2*n+1];
		boolean Visited[]=new boolean[n];
		for(int i=0;i<G.V;i++){		
			if(Visited[i]==false){
				numb=DFS(G,i,numb,Sequence,Visited);
				Sequence[numb]=-1;
				numb++;
			}
		}
		Sequence[2*n]=numb;
		return Sequence;
	}
	
	private static int BFS(SimpleGraph G,int v,int numb,int Sequence[],boolean Visited[]){
		Visited[v]=true;
		Sequence[numb]=v;
		numb++;
		Queue<Integer> Q=new LinkedList<Integer>();
		for(int i=0;i<G.V;i++){
			if(G.E[v][i]==true && Visited[i]==false){
				Visited[i]=true;
				Q.add(i);
			}
		}
		int x;
		while(!Q.isEmpty()){
			x=Q.remove();
			numb=BFS( G, x, numb, Sequence, Visited);
		}
		return numb;
	}
	
	public static int[] BreadthSearch(SimpleGraph G){
		int numb=0;
		int Sequence[]= new int[2*n+1];
		boolean Visited[]=new boolean[n];
		for(int i=0;i<G.V;i++){		
			if(Visited[i]==false){
				numb=BFS(G,i,numb,Sequence,Visited);
				Sequence[numb]=-1;
				numb++;
			}
		}
		Sequence[2*n]=numb;
		return Sequence;
	
	}
	
	//Basic algorithms
	public static boolean Connected(SimpleGraph G,int v, int u){
		int numb=0;
		int Sequence[]= new int[101];
		boolean Visited[]=new boolean[50];
		DFS(G,v,numb,Sequence,Visited);
		//DFS
		if(Visited[u]==true) return true;
		else return false;
	}
	
	public static boolean Connected(SimpleGraph G) {
		for(int i=0;i<G.V-1;i++) {
			for(int j=i+1;j<G.V;j++) {
				if(!G.Connected(G, i, j)) return false;
			}
		}
		return true;
	}
	
	public void Print(){
		System.out.println(V);
		for(int i=0;i<V;i++){
			for(int j=0;j<=i;j++){
				if(E[i][j]==true) System.out.print(i+"-"+j+"  ");
			}
		}
	}
	
	
	
}
