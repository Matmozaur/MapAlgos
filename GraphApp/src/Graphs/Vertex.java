package Graphs;

import java.awt.Color;
import java.awt.Graphics;

class Vertex {
	private int x;
	private int y;
	private int diam;
	private Color color;
	private int numb;
	private String label;
	
	public Vertex(int x, int y, int numb, String label) {
		super();
		this.x = x;
		this.y = y;
		this.numb = numb;
		this.label = label;
		this.color=Main.vcolor;
		this.diam=Main.diam;
	}

	int getX() {
		return x;
	}

	void setX(int x) {
		this.x = x;
	}

	int getY() {
		return y;
	}

	void setY(int y) {
		this.y = y;
	}

	int getDiam() {
		return diam;
	}

	void setDiam(int diam) {
		this.diam = diam;
	}

	Color getColor() {
		return color;
	}

	void setColor(Color color) {
		this.color = color;
	}

	int getNumb() {
		return numb;
	}

	void setNumb(int numb) {
		this.numb = numb;
	}

	String getLabel() {
		return label;
	}

	void setLabel(String label) {
		this.label = label;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, diam, diam);
	}
	

}
