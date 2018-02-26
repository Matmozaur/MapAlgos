package Graphs;
import java.util.Queue;
import java.util.LinkedList;

public class SimpleGraph extends Graph {
	
	boolean E[][]=new boolean[n][n];
	public SimpleGraph(int v,boolean e[][]) {
		super(v);
		E=e;
	}
	
	//panel algorithms
	
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
		BFS(G,v,Visited,panel);
	}
	
	private  void BFS(SimpleGraph G,int v,boolean Visited[],GraphPanel panel){
		Visited[v]=true;
		last=v;
		Queue<Integer> Q=new LinkedList<Integer>();
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
			BFS( G, x, Visited, panel);
		}
	}
	
	public  void BreadthSearch(SimpleGraph G,GraphPanel panel){
		boolean Visited[]=new boolean[n];
		for(int i=0;i<G.V;i++){		
			if(Visited[i]==false){
				last=-1;
				color(i,panel);
				BFS(G,i,Visited,panel);
			}
		}

	
	}
	
	
	//Pure graphs
	
	private int DFS(SimpleGraph G,int v,int numb,int Sequence[],boolean Visited[]){
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
	
	public  int[] DeepSearch(SimpleGraph G){
		last=-1;
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
	
	private  int BFS(SimpleGraph G,int v,int numb,int Sequence[],boolean Visited[]){
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
	
	public  int[] BreadthSearch(SimpleGraph G){
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
	
	public  boolean Connected(SimpleGraph G,int v, int u){
		//DFS
		int numb=0;
		int Sequence[]= new int[101];
		boolean Visited[]=new boolean[50];
		DFS(G,v,numb,Sequence,Visited);
		//DFS
		if(Visited[u]==true) return true;
		else return false;
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
