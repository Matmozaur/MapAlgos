package view;

import controller.mouse.Clicker;
import model.settings.Settings;
import model.settings.Type;
import model.elements.Edge;
import model.elements.Vertex;
import model.elements.WeightEdge;
import model.graphs.representation.WeightGraph;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel {

    GraphPanel(GraphApp parent){
        setLayout(null);
        G = new WeightGraph(this);
        settings=parent.getSettings();
        this.myParent=parent;
    }

    private Vertex currentVertex=null;
    private WeightGraph G;
    private int counter=0;
    private Settings settings;
    private final GraphApp myParent;

    public GraphApp getMyParent() {
        return myParent;
    }

    public Settings getSettings() {
        return settings;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Vertex getCurrentVertex() {
        return currentVertex;
    }

    public void setCurrentVertex(Vertex currentVertex) {
        if(this.currentVertex==currentVertex && this.currentVertex!=null) {
            this.setCurrentVertex(null);
            return;
        }
        this.unselect(this.currentVertex);
        this.currentVertex = currentVertex;
        this.select(currentVertex);
    }

    public void setG(WeightGraph g) {
        G = g;
        this.update(this.getGraphics());
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }


    private void select(Vertex v) {
        if(v==null) return;
        v.setColor(settings.colorS);
        this.update(this.getGraphics());
    }

    public void unselect(Vertex a, Vertex b) {
        a.setColor(settings.vcolor);
        b.setColor(settings.vcolor);
        this.update(this.getGraphics());
    }

    public void unselect(Vertex a) {
        if(a==null) return;
        a.setColor(settings.vcolor);
        this.update(this.getGraphics());
    }

    @Override
    public void paint(Graphics g) {
        for (Edge c : G.getEdges()) {
            c.draw(g);
        }

        for (Vertex c : G.getVertexes()) {
            c.draw(g);
        }
    }

    /**
     * returns vertex from location (u,v), if it doesnt exists returns null
     *
     * @param u first vertex
     * @param v secound vertex
     * @return  vertex from location (u,v)
     */
    public Vertex getVertex(int u, int v) {
        for (Vertex c : G.getVertexes()) {
            if (c.getX() <= u + 1.5*settings.vdiam && c.getX() >= u - 1.5*settings.vdiam &&
                    c.getY() <= v + 1.5*settings.vdiam && c.getY() >= v - 1.5*settings.vdiam) {
                return c;
            }
        }
        return null;
    }

    /**
     * returns edge between vertexes u and v, if it doesnt exists returns null
     *
     * @param u first vertex
     * @param v secound vertex
     * @return edge between vertexes u and v
     */
    public Edge getEdge(Vertex u, Vertex v) {
        for (Edge e : G.getEdges()) {
            if (e.getMySource() == u && e.getMyTarget() == v || e.getMySource() == v && e.getMyTarget() == u) {
                return e;
            }
        }
        return null;
    }


    /**
     * adds a vertex to a panel
     *
     * @param c new vertex
     */
    public void addVertex(Vertex c) {
        if(this.counter< Settings.n) {
            if (getVertex(c.getX(), c.getY()) == null) {
                this.counter++;
                G.add(c);
                this.setCurrentVertex(null);
                this.update(this.getGraphics());
            }
            else {
                this.setCurrentVertex(getVertex(c.getX(), c.getY()));
            }
        }
    }

    /**
     * adds a edge to a panel
     *
     * @param c new edge
     */
    public void addEdge(Edge c) {
        settings.GraphType= Type.SIMPLE;
        if (getEdge(c.getMySource(), c.getMyTarget()) == null) {
            G.add(c);
            unselect(c.getMySource(), c.getMyTarget());
            this.update(this.getGraphics());
        }
    }

    public void addWeightEdge(WeightEdge we) {
        settings.GraphType=Type.SIMPLEWEIGHT;
        if (getEdge(we.getMySource(), we.getMyTarget()) == null) {
            G.add(we);
            unselect(we.getMySource(), we.getMyTarget());
            this.update(this.getGraphics());
        }
    }

    /**
     * remove vertex from panel
     */
    public void removeVertex(Vertex v) {
        G.remove(v);
        this.setOpaque(false);
        this.repaint();
    }

    /**
     * remove edge from panel
     */
    public void removeEdge(Edge e) {
        G.removeE(e);
        if(G.getEdges().isEmpty()) settings.GraphType=Type.UNDEFINED;
        this.setOpaque(false);
        this.repaint();
    }

    public WeightGraph getG() {
        return G;
    }

    String allInfo(){
        return G.allInfo();
    }

    String basicInfo(){
        return G.basicInfo();
    }

    void showLabels() {
        settings.visibility=true;
        this.setOpaque(false);
        this.repaint();
    }

    void hideLabels() {
        settings.visibility=false;
        this.setOpaque(false);
        this.repaint();
    }

    void refresh() {
        this.G = new WeightGraph(this);
        this.counter = 0;
        settings.GraphType=Type.UNDEFINED;
        for(int i=0;i<getMouseListeners().length;i++){
            removeMouseListener(getMouseListeners()[i]);
        }
        addMouseListener(new Clicker(this));
        this.setOpaque(false);
        this.repaint();

    }

    void clear() {
        G.clear();
        this.setOpaque(false);
        this.repaint();
    }


}
