package graph.app;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SimpleWeightEdge extends Edge {
	private int weight;

	public SimpleWeightEdge(Vertex a, Vertex b, String Label, int weight) {
		super(a, b, Label);
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
    public void draw(Graphics g2d) {
        //g2d.setColor(color);
        g2d.setColor(this.getColor());  // for fun
        Graphics2D g2 = (Graphics2D) g2d;
        g2.setStroke(new BasicStroke(3));
        int x1 = b.getX() + Main.diam / 2;
        int y1 = b.getY() + Main.diam / 2;
        int x2 = a.getX() + Main.diam / 2;
        int y2 = a.getY() + Main.diam / 2;

        g2d.drawLine(x1, y1, x2, y2);

        // printing the weight of current edge
        int midx = (x1 + x2) / 2;
        int midy = (y1 + y2) / 2;
        Integer weight = this.getWeight();
        g2d.drawString(weight.toString(), midx + 12, midy + 12);
    }

}
