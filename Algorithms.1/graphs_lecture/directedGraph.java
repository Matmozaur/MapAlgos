package graphs_lecture;

public class directedGraph extends graph {

	int E[][]=new int[n][n];
	public directedGraph(int v,int e[][]) {
		super(v);
		E=e;
		
	}
}
