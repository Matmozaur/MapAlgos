package graph.app;

import java.awt.Color;
import java.awt.Graphics;

class Vertex {
	private int x;
	private int y;
	private int diam;
	private Color color;
	private int numb;
	private String label;
	private boolean visible;

	
	public Vertex(int x, int y, int numb, String label) {
		super();
		this.x = x;
		this.y = y;
		this.numb = numb;
		this.label = label;
		this.color=Main.vcolor;
		this.diam=Main.diam;
		this.visible=false;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
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




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Vertex other = (Vertex) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	/**
	 * draws vertex with color set in Main class 
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, diam, diam);
		if(visible) {
			int n=numb+1;
			g.drawString(n+"", x+diam/2, y-(int)0.6*diam);
		}
	}
}