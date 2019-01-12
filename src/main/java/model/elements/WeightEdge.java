package model.elements;

import model.settings.Settings;

import java.awt.*;
import java.io.Serializable;

public class WeightEdge extends Edge implements Serializable {
    private static final long serialVersionUID = -1562336722321L;
    /**
     * edges weight
     */
    private final int myWeight;

    public WeightEdge(Vertex source, Vertex target, int weight, Settings settings) {
        super(source, target, settings);
        this.myWeight = weight;
    }

    @Override
    public int getMyWeight() {
        return this.myWeight;
    }


    @Override
    public void draw(Graphics g2d) {
        if(g2d!=null) {
            g2d.setColor(this.getColor());
            Graphics2D g2 = (Graphics2D) g2d;
            g2.setStroke(new BasicStroke(3));
            int x1 = mytarget.getX() + settings.vdiam / 2;
            int y1 = mytarget.getY() + settings.vdiam / 2;
            int x2 = mysource.getX() + settings.vdiam / 2;
            int y2 = mysource.getY() + settings.vdiam / 2;
            g2d.drawLine(x1, y1, x2, y2);
            // printing the weight of current edge
            int midx = (x1 + x2) / 2;
            int midy = (y1 + y2) / 2;
            Font myFont = new Font("Courier New", Font.BOLD, 18);
            g2d.setFont(myFont);
            int weight = this.getMyWeight();
            g2d.setColor(settings.colorLabel);
            g2d.drawString(Integer.toString(weight), midx + 22, midy + 22);
        }
    }

    @Override
    public String toString(){
        return super.toString()+" ("+myWeight+")";
    }


}
