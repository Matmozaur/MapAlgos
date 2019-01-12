package model.elements;
import model.settings.Settings;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.awt.*;
import java.io.Serializable;

/**
 * Representation of edge in simple graph
 * @author Matmozaur
 *
 */
public class Edge extends DefaultWeightedEdge implements Serializable {
    private static final long serialVersionUID = -15623738321L;
    /**
     * one of edges nodes
     */
    final Vertex mysource;
    /**
     * one of edges nodes
     */
    final Vertex mytarget;


    private Color color;

    /**
     * Grphic settings
     */
    final Settings settings;
    /**
     * Create egde with nodes in a and b
     * @param mysource begin node
     * @param mytarget end node
     */
    public Edge(Vertex mysource, Vertex mytarget, Settings settings) {
        super();
        this.mysource = mysource;
        this.mytarget = mytarget;
        color= settings.ecolor;
        this.settings=settings;
    }


    Color getColor() {
        return color;
    }

    public Vertex getMyTarget() {
        return mytarget;
    }

    public Vertex getMySource() {
        return mysource;
    }

    public Vertex getSource(){
        return (Vertex) super.getSource();
    }

    public Vertex getTarget(){
        return (Vertex) super.getTarget();
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mysource == null) ? 0 : mysource.hashCode());
        result = prime * result + ((mytarget == null) ? 0 : mytarget.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        Edge other = (Edge) obj;
        if (mysource == null) {
            if (other.mysource != null)
                return false;
        } else if (!mysource.equals(other.mysource)&&!mysource.equals(other.mytarget))
            return false;
        if (mytarget == null) {
            return other.mytarget == null;
        } else return mytarget.equals(other.mytarget) || mytarget.equals(other.mysource);
    }


    /**
     * draws edge with color set in GraphApp class @see {@link Settings#ecolor}
     * @param g graphic
     */
    public void draw(Graphics g) {
        if (g != null) {
            g.setColor(color);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            g.drawLine(mysource.getX() + settings.vdiam / 2, mysource.getY() + settings.vdiam / 2 / 2,
                    mytarget.getX() + settings.vdiam / 2 / 2, mytarget.getY() + settings.vdiam / 2 / 2);
        }
    }

    public void setColor(Color colorEx) {
        this.color=colorEx;
    }

    public int getMyWeight(){
        return 1;
    }

    @Override
    public String toString(){
        return "("+ mysource +","+ mytarget +")";
    }
}