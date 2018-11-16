package representations;


import view.Main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

/**
 * representation of directed weight edge
 * @author Matmozaur
 *
 */
public class WeightEdge extends Edge {

    /**
     * Edges weight
     */
    private int weight;
    private Color color;

    public WeightEdge(Vertex a, Vertex b, String Label, int weight) {
        super(a, b, Label);
        this.weight = weight;
        this.color = Main.getEcolor();
        // TODO Auto-generated constructor stub
    }

    public int getWeight() {
        return weight;   // for test cases
        // return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
        Integer weight = this.getWeight();
        g2d.drawString(weight.toString(), midx + 12, midy + 12);

        // create an AffineTransform
        // and a triangle centered on (0,0) and pointing downward
        // somewhere outside Swing's paint loop
        AffineTransform tx = new AffineTransform();
        Line2D.Double line = new Line2D.Double(x1, y1, x2, y2);

        Polygon arrowHead = new Polygon();
        arrowHead.addPoint(0, 10);
        arrowHead.addPoint(-10, -10);
        arrowHead.addPoint(10, -10);

        int step = 12;
        double dx = (x2 - x1) / step;
        double dy = (y2 - y1) / step;

        tx.setToIdentity();
        double angle = Math.atan2(line.y2 - line.y1, line.x2 - line.x1);
        tx.translate(line.x2 - dx, line.y2 - dy);
        tx.rotate((angle - Math.PI / 2d));

        Graphics2D g = (Graphics2D) g2d.create();
        g.setTransform(tx);
        g.fill(arrowHead);
        g.dispose();
    }

}