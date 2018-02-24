package graphs_lecture;

public final class runTest {
	public static void main(String[] args) {
/*
		boolean E[][]=new boolean[50][50];
		E[0][4]=E[4][0]=true;
		E[0][2]=E[2][0]=true;
		E[4][2]=E[2][4]=true;
		E[3][1]=E[1][3]=true;
		simpleGraph G=new simpleGraph(5,E);
		int Tree[]=new int[101];
		Tree=simpleGraph.BreadthSearch(G);
		for(int i=0;i<Tree[100];i++){
			if(Tree[i]!=-1)
				System.out.print(Tree[i]+" ");
			else 
				System.out.print("| ");
		}
*/
		simpleGraph G=new simpleGraph(5,new boolean [50][50]);
			G.E[0][4]=G.E[4][0]=true;
			G.E[0][2]=G.E[2][0]=true;
			G.E[4][2]=G.E[2][4]=true;
			G.E[3][1]=G.E[1][3]=true;
			G.E[4][1]=G.E[1][4]=true;
			G.E[3][2]=G.E[2][3]=true;
		//G.Print();
		WeightGraph S=new WeightGraph(G.V,G.E,new int [50][50]);
			S.W[0][4]=S.W[4][0]=3;
			S.W[0][2]=S.W[2][0]=2;
			S.W[4][2]=S.W[2][4]=5;
			S.W[3][1]=S.W[1][3]=1;
			S.W[4][1]=S.W[1][4]=10;
			S.W[3][2]=S.W[2][3]=1;
		simpleGraph T = WeightGraph.kraskal(S);
		T.Print();
	}
		
}
