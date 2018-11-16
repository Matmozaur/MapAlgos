package representations;

import view.Main;

import java.awt.Color;
import java.awt.Graphics;



/**
 * representation of vertex
 * @author Matmozaur
 *
 */
public class Vertex {
    /**
     * horizontal coordinate in panel
     */
    private int x;
    /**
     * veritical coordinate in panel
     */
    private int y;
    /**
     * diameter of vertex
     */
    private int diam;
    /**
     * vertexes color
     */
    private Color color;
    /**
     * number of vertex (primary indentifier
     */
    private int numb;
    /**
     * vertexes label
     */
    private String label;
    /**
     * visibility of vertexes label
     */
    private boolean visible;

    public Vertex(int x, int y, int numb, String label) {
        super();
        this.x = x;
        this.y = y;
        this.numb = numb;
        this.label = label;
        this.color=Main.getVcolor();
        this.diam= Main.getDiam();
        this.visible=false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDiam() {
        return diam;
    }

    public void setDiam(int diam) {
        this.diam = diam;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
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
        if(this.label != null) {
            g.drawString(this.label, x+diam/2, y+(int)2.6*diam);
        }
    }
}