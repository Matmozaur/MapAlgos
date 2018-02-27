package graph.app;

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
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + numb;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (numb != other.numb)
			return false;
		return true;
	}
	/**
	 * draws edge with color set in Main class 
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(a.getX()+Main.diam/2, a.getY()+Main.diam/2, b.getX()+Main.diam/2, b.getY()+Main.diam/2);
	}
}
