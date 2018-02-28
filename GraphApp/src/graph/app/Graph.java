package graph.app;

import java.awt.Color;

public abstract class Graph {
	static int n=30;
	protected  int last=-1;
	static Color color=Color.RED;
	static long t=500;
	int V;
	
	
	public Graph(int v){
		V=v;
	}
	/**
	 * swaps 2 elements
	 * @param A
	 * @param B
	 */
	public static void swap(int A[],int B[]){
		int C[]=A;
		A=B;
		B=C;
	}
	/**
	 * gives vertex a from GraphPanel panel color from Graph class
	 * @param a
	 * @param panel
	 */
	void color(int a,GraphPanel panel) {
		for(Vertex c:panel.vertexes) {
			if(c.getNumb()==a) {
				c.setColor(color);
				panel.update(panel.getGraphics());
			}
		}
	}
	/**
	 *gives edge  from a to b from GraphPanel panel color from Graph class
	 * @param a
	 * @param b
	 * @param panel
	 */
	void color(int a,int b, GraphPanel panel) {
		for(Edge c:panel.edges) {
			if((c.a.getNumb()==a&&c.b.getNumb()==b)||(c.b.getNumb()==a&&c.a.getNumb()==b)) {
				c.setColor(color);
				panel.update(panel.getGraphics());
			}
		}
	}
}
