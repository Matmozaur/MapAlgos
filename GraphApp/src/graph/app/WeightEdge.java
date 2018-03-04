package graph.app;

import java.awt.Graphics;

class WeightEdge extends Edge {

	private int weight;
	public WeightEdge(Vertex a, Vertex b, String Label, int weight) {
		super(a, b, Label);
		this.weight=weight;
		// TODO Auto-generated constructor stub
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
