package model.elements;

import model.settings.Settings;

import java.awt.*;

public class WeightEdge extends Edge {
    /**
     * edges weight
     */
    private int myWeight;

    public WeightEdge(Vertex source, Vertex target, int weight, Settings settings) {
        super(source, target, settings);
        this.myWeight = weight;
    }

    private WeightEdge(Edge e,Settings settings){
        super(e.mysource,e.mytarget,settings);
        if(e instanceof WeightEdge){
            this.myWeight=((WeightEdge) e).getMyWeight();
        }
        else this.myWeight=1;
    }

    public static WeightEdge createReverseWeightEdge(Edge e,Settings settings){
        WeightEdge f= new WeightEdge(e,settings);
        f.myWeight=-f.myWeight;
        return f;
    }


    @Override
    public int getMyWeight() {
        return this.myWeight;
    }


    public void draw(Graphics g2d) {
        //g2d.setColor(color);
        g2d.setColor(this.getColor());  // for fun
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
        Font myFont = new Font ("Courier New", Font.BOLD, 17);
        g2d.setFont (myFont);
        int weight = this.getMyWeight();
        g2d.drawString(Integer.toString(weight), midx + 12, midy + 12);
    }

    @Override
    public String toString(){
        return super.toString()+" ("+myWeight+")";
    }


}
