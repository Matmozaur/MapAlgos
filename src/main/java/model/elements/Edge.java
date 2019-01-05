package model.elements;
import model.settings.Settings;
import org.jgrapht.graph.DefaultEdge;

import java.awt.*;

/**
 * Representation of edge in simple graph
 * @author Matmozaur
 *
 */
public class Edge extends DefaultEdge{

    /**
     * one of edges nodes
     */
    final Vertex source;
    /**
     * one of edges nodes
     */
    final Vertex target;


    private Color color;
// --Commented out by Inspection START (05.01.19 22:10):
//    /**
//     * number of edge (identifier)
//     */
//    private int numb;
// --Commented out by Inspection STOP (05.01.19 22:10)
    /**
     * Grphic settings
     */
    final Settings settings;
    /**
     * Create egde with nodes in a and b
     * @param source begin node
     * @param target end node
     */
    public Edge(Vertex source, Vertex target, Settings settings) {
        super();
        this.source = source;
        this.target = target;
        color= settings.ecolor;
        this.settings=settings;
    }
    Color getColor() {
        return color;
    }

    @Override
    public Vertex getTarget() {
        return target;
    }

    @Override
    public Vertex getSource() {
        return source;
    }




    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
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
        Edge other = (Edge) obj;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (target == null) {
            return other.target == null;
        } else return target.equals(other.target);
    }
    /**
     * draws edge with color set in GraphApp class @see {@link Settings#ecolor}
     * @param g graphic
     */
    public void draw(Graphics g) {
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g.drawLine(source.getX()+ settings.vdiam/2, source.getY()+settings.vdiam/2/2,
                target.getX()+settings.vdiam/2/2, target.getY()+settings.vdiam/2/2);
    }

    public void setColor(Color colorEx) {
        this.color=colorEx;
    }
}