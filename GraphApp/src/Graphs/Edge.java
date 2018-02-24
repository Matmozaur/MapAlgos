package Graphs;

import java.awt.Color;
import java.awt.Graphics;

public class Edge {

	Vertex a;
	Vertex b;
	private Color color;
	private int numb;
	private String label;
	
	public Edge(Vertex a, Vertex b,String Label) {
		super();
		this.a = a;
		this.b = b;
		this.label=Label;
		this.color=Main.ecolor;
	}
	Color getColor() {
		return color;
	}
	void setColor(Color color) {
		this.color = color;
	}
	public Vertex getA() {
		return a;
	}
	public void setA(Vertex a) {
		this.a = a;
	}
	public Vertex getB() {
		return b;
	}
	public void setB(Vertex b) {
		this.b = b;
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(a.getX()+Main.diam/2, a.getY()+Main.diam/2, b.getX()+Main.diam/2, b.getY()+Main.diam/2);
	}
}
