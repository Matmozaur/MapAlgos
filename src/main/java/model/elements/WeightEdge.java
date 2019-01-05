package model.elements;

import model.settings.Settings;

import java.awt.*;

public class WeightEdge extends Edge {
    /**
     * edges weight
     */
    private final int weight;

    public WeightEdge(Vertex source, Vertex target, String Label, int weight, Settings settings) {
        super(source, target, settings);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

// --Commented out by Inspection START (05.01.19 21:57):
//    public void setWeight(int weight) {
//        this.weight = weight;
//    }
// --Commented out by Inspection STOP (05.01.19 21:57)

    public void draw(Graphics g2d) {
        //g2d.setColor(color);
        g2d.setColor(this.getColor());  // for fun
        Graphics2D g2 = (Graphics2D) g2d;
        g2.setStroke(new BasicStroke(3));
        int x1 = target.getX() + settings.vdiam / 2;
        int y1 = target.getY() + settings.vdiam / 2;
        int x2 = source.getX() + settings.vdiam / 2;
        int y2 = source.getY() + settings.vdiam / 2;

        g2d.drawLine(x1, y1, x2, y2);

        // printing the weight of current edge
        int midx = (x1 + x2) / 2;
        int midy = (y1 + y2) / 2;
        Font myFont = new Font ("Courier New", Font.BOLD, 17);
        g2d.setFont (myFont);
        int weight = this.getWeight();
        g2d.drawString(Integer.toString(weight), midx + 12, midy + 12);
    }
}
