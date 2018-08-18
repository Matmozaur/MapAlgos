package main.application;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JPanel;

import graphs.application.Edge;
import graphs.application.SimpleWeightEdge;
import graphs.application.Vertex;
import graphs.representation.Graph;
import graphs.representation.WeightGraph;

public class GraphPanel extends JPanel {

	public Vertex currentVertex=null;
	public List<Vertex> vertexes = new LinkedList<Vertex>();
    public List<Edge> edges = new LinkedList<Edge>();
    public  int counter = 0;
    public  int n = Graph.n;
    public WeightGraph G = new WeightGraph(0, new boolean[n][n],new int[n][n]);
    public List<SimpleWeightEdge> simpleW = new LinkedList<SimpleWeightEdge>();

    public void setCurrentVertex(Vertex currentVertex) {
    	if(this.currentVertex==currentVertex && this.currentVertex!=null) {
    		this.setCurrentVertex(null);
    		return;
    	}
    	this.unselect(this.currentVertex);
		this.currentVertex = currentVertex;
		this.select(currentVertex);
	}
    
    public void select(Vertex v) {
    	if(v==null) return;
        v.setColor(Color.blue);
        this.update(this.getGraphics());
    }

    public void unselect(Vertex a, Vertex b) {
        a.setColor(Main.getVcolor());
        b.setColor(Main.getVcolor());
        this.update(this.getGraphics());
    }
    public void unselect(Vertex a) {
    	if(a==null) return;
        a.setColor(Main.getVcolor());
        this.update(this.getGraphics());
    }

    public GraphPanel() {
        setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        for (Edge c : edges) {
            c.draw(g);
        }
        for (SimpleWeightEdge we : simpleW) {
            we.draw(g);
        }
        for (Vertex c : vertexes) {
            c.draw(g);
        }
    }

    /**
     * returns vertex from location (u,v), if it doesnt exists returns null
     *
     * @param u
     * @param v
     * @return
     */
    public Vertex getVertex(int u, int v) {
        for (Vertex c : vertexes) {
            if (c.getX() <= u + Main.getDiam() && c.getX() >= u - Main.getDiam() &&
            		c.getY() <= v + Main.getDiam() && c.getY() >= v - Main.getDiam()) {
                return c;
            }
        }
        return null;
    }

    /**
     * returns edge between vertexes u and v, if it doesnt exists returns null
     *
     * @param u
     * @param v
     * @return
     */
    public Edge getEdge(Vertex u, Vertex v) {
        for (Edge e : edges) {
            if (e.getA() == u && e.getB() == v || e.getA() == v && e.getB() == u) {
                return e;
            }
        }
        return null;
    }

    public SimpleWeightEdge getSimpleWeightEdge(Vertex u, Vertex v) {
        for (SimpleWeightEdge e : simpleW) {
            if (e.getA() == u && e.getB() == v || e.getA() == v && e.getB() == u) {
                return e;
            }
        }
        return null;
    }
    /**
     * adds a vertex to a panel
     *
     * @param c
     */
    public void addVertex(Vertex c) {
        if(this.counter<this.n) {
        	if (getVertex(c.getX(), c.getY()) == null) {
            this.counter++;
            vertexes.add(c);
            G.V++;
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
     * @param c
     */
    public void addEdge(Edge c) {
    	Main.setGRAPH(Type.SIMPLE);;
        if (getEdge(c.getA(), c.getB()) == null) {
            edges.add(c);
            unselect(c.getA(), c.getB());
            G.E[c.a.getNumb()][c.b.getNumb()] = G.E[c.b.getNumb()][c.a.getNumb()] = true;
            this.update(this.getGraphics());
        }
        /*
        int i=0;
        for (Edge e : edges) {
        	i++;
        }
        System.out.println(i+" ");
        */
    }

    public void addSimpleWeightEdge(SimpleWeightEdge we) {
    	Main.setGRAPH(Type.SIMPLEWEIGHT);
    	Main.getMnNewMenu_1().setEnabled(false);
    	//Main.getBtnVertex_1().setEnabled(false);
        if (getSimpleWeightEdge(we.getA(), we.getB()) == null) {
            simpleW.add(we);
            unselect(we.getA(), we.getB());
            G.E[we.a.getNumb()][we.b.getNumb()] = G.E[we.b.getNumb()][we.a.getNumb()] = true;
            G.W[we.a.getNumb()][we.b.getNumb()] = G.W[we.b.getNumb()][we.a.getNumb()] = we.getWeight();
            this.update(this.getGraphics());
        }
    }

    /**
     * remove vertex from panel
     */
    public void removeVertex(Vertex v) {
        ListIterator<Edge> iterE = edges.listIterator();
        while (iterE.hasNext()) {
            Edge e = iterE.next();
            if (e.getA() == v || e.getB() == v) {
                iterE.remove();
                this.removeEdge(e);
            }
        }
        ListIterator<SimpleWeightEdge> iterW = simpleW.listIterator();
        while (iterW.hasNext()) {
            SimpleWeightEdge e = iterW.next();
            if (e.getA() == v || e.getB() == v) {
            	iterW.remove();
            	this.removeSimpleWeightEdge(e);
            }
        }
        /*for(Edge e:this.edges) {
                if(e.getA()==v||e.getB()==v) {
				int a=this.edges.indexOf(e);
				this.edges.remove(a);
			}
		}*/
        this.counter--;
        ListIterator<Vertex> iterV = vertexes.listIterator();
        while (iterV.hasNext()) {
            Vertex u = iterV.next();
            if (u == v) {
                iterV.remove();
                //trzeba poprawi� remova
                G.remove(v.getNumb());
            }
        }
        for (Vertex u : vertexes) {
            if (u.getNumb() > v.getNumb()) u.setNumb(u.getNumb() - 1);
        }
		/*for(Vertex u:vertexes) {
			if(u==v) {
				int a=vertexes.indexOf(u);
				vertexes.remove(a);
				G.remove(v.getNumb());
			}
		}*/
        this.setOpaque(false);
        this.repaint();
    }

    /**
     * remove edge from panel
     */
    public void removeEdge(Edge e) {
        int a;
        for (Edge f : edges) {
            if (f == e) {
                a = edges.indexOf(f);
                unselect(e.getA(), e.getB());
                edges.remove(a);
                G.E[e.getA().getNumb()][e.getB().getNumb()] = G.E[e.getB().getNumb()][e.getA().getNumb()] = false;
                break;
            }
        }
        if(edges.isEmpty()) Main.setGRAPH(Type.UNDEFINED);
        this.setOpaque(false);
        this.repaint();
    }
    
    public void removeSimpleWeightEdge(Edge e) {
        int a;
        for (SimpleWeightEdge f : simpleW) {
            if (f == e) {
                a = simpleW.indexOf(f);
                unselect(e.getA(), e.getB());
                simpleW.remove(a);
                G.E[e.getA().getNumb()][e.getB().getNumb()] = G.E[e.getB().getNumb()][e.getA().getNumb()] = false;
                G.W[e.getA().getNumb()][e.getB().getNumb()] = G.W[e.getB().getNumb()][e.getA().getNumb()] = 0;
                break;
            }
        }
        if(simpleW.isEmpty()) {
        	Main.setGRAPH(Type.UNDEFINED);
        	Main.getMnNewMenu_1().setEnabled(true);
        	//Main.getBtnVertex_1().setEnabled(true);
        }
        this.setOpaque(false);
        this.repaint();
    }
    
    public void showLabels() {
    	for (Vertex c : vertexes) {
            c.setVisible(true);
        }
        this.setOpaque(false);
        this.repaint();
    }
    
    public void hideLabels() {
    	for (Vertex c : vertexes) {
    		c.setVisible(false);
        }
        this.setOpaque(false);
        this.repaint();
    }


    
    
    
    
    
    public void refresh() {
    	WeightGraph G = new WeightGraph(0, new boolean[n][n],new int[n][n]);
        this.G = G;
        this.vertexes.clear();
        this.edges.clear();
        this.simpleW.clear();
        this.counter = 0;
        Main.setGRAPH(Type.UNDEFINED);
    	Main.getMnNewMenu_1().setEnabled(true);
    	Main.getBtnVertex_1().setEnabled(true);
        this.setOpaque(false);
        this.repaint();
        
    }

    public void clear() {
        for (Edge c : edges) {
            c.setColor(Main.getEcolor());
        }
        for (SimpleWeightEdge c : simpleW) {
            c.setColor(Main.getEcolor());
        }
        for (Vertex c : vertexes) {
            c.setColor(Main.getVcolor());
            c.setLabel(null);
        }
        this.setOpaque(false);
        this.repaint();
    }

}
