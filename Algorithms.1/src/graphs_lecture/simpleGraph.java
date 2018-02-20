package graphs_lecture;
import java.util.Queue;
import java.util.LinkedList;

public class simpleGraph extends graph {
	
	boolean E[][]=new boolean[n][n];
	public simpleGraph(int v,boolean e[][]) {
		super(v);
		E=e;
	}
	
	private static int DFS(simpleGraph G,int v,int numb,int Sequence[],boolean Visited[]){
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
	public static int[] DeepSearch(simpleGraph G){
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
	
	private static int BFS(simpleGraph G,int v,int numb,int Sequence[],boolean Visited[]){
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
	
	public static int[] BreadthSearch(simpleGraph G){
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
	
	public static boolean Connected(simpleGraph G,int v, int u){
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
