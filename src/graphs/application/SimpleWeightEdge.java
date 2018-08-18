package graphs.application;

import java.awt.*;

import main.application.Main;
/**
 * representation of undirected weight edge
 * @author Matmozaur
 *
 */
public class SimpleWeightEdge extends Edge {
	/**
	 * edges weight
	 */
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
        int x1 = b.getX() + Main.getDiam() / 2;
        int y1 = b.getY() + Main.getDiam() / 2;
        int x2 = a.getX() + Main.getDiam() / 2;
        int y2 = a.getY() + Main.getDiam() / 2;

        g2d.drawLine(x1, y1, x2, y2);

        // printing the weight of current edge
        int midx = (x1 + x2) / 2;
        int midy = (y1 + y2) / 2;
        Font myFont = new Font ("Courier New", 1, 17);
        g2d.setFont (myFont);
        Integer weight = this.getWeight();
        g2d.drawString(weight.toString(), midx + 12, midy + 12);
    }
}
