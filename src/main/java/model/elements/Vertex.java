package model.elements;

import model.settings.Settings;

import java.awt.Color;
import java.awt.Graphics;



/**
 * representation of vertex
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
     * graphic settings
     */
    private final Settings settings;
    /**
     * vertexes color
     */
    private Color color;
    /**
     * number of vertex (primary indentifier)
     */
    private int numb;
    /**
     * vertexes label
     */
    private String label;



    public Vertex(int x, int y, int numb, String label,Settings settings) {
        super();
        this.x = x;
        this.y = y;
        this.numb = numb;
        if(label!=null){
            this.label = label;
        }
        else{
            this.label="Place"+(numb+1)+"";
        }
        this.color= settings.vcolor;
        this.settings=settings;
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
        return y == other.y;
    }

    /**
     * draws vertex with color set in GraphApp class
     * @param g graphic
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, settings.vdiam, settings.vdiam);
        if(settings.visibility&&this.label!=null) {
            g.drawString(label, x+settings.vdiam/2, y-(int)(0.1*settings.vdiam));
        }
    }

    @Override
    public String toString() {
        if(label==null) return "(" + numb + ")";
        return label+"(" + numb + ")";
    }
}