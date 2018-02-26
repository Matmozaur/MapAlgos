package graph.app;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

class GraphPanel extends JPanel {
	
	List<Vertex> vertexes=new LinkedList<Vertex>();
	List<Edge> edges=new LinkedList<Edge>();
	static int counter=0;
	static int n=Graph.n;
	boolean E[][]=new boolean[n][n];
	SimpleGraph G=new SimpleGraph(0,E);
	
	public GraphPanel() {
		setLayout(null);
	}
	
	@Override
	public void paint(Graphics g) {
		for(Edge c:edges) {
			c.draw(g);
		}
		for(Vertex c:vertexes) {
			c.draw(g);
		}
	}
	
	/**
	 * returns vertex from location (u,v), if it doesnt exists returns null
	 * @param u
	 * @param v
	 * @return
	 */
	public  Vertex getVertex(int u,int v) {
		for(Vertex c:vertexes) {
			if(c.getX()<=u+Main.diam&&c.getX()>=u-Main.diam&&c.getY()<=v+Main.diam&&c.getY()>=v-Main.diam) {
				return c;
			}
		}
		return null;
	}

	/**
	 * adds a vertex to a panel
	 * @param c
	 */
	public void addVertex(Vertex c) {
		if(getVertex(c.getX(),c.getY())==null) {
		vertexes.add(c);
		G.V++;
		this.update(this.getGraphics());
		}
	}
	/**
	 * adds a edge to a panel
	 * @param c
	 */
	public void addEdge(Edge c) {
		edges.add(c);
		G.E[c.a.getNumb()][c.b.getNumb()]=G.E[c.b.getNumb()][c.a.getNumb()]=true;
		this.update(this.getGraphics());
	}
	
	public void clear() {
		boolean E[][]=new boolean[n][n];
		this.E=E;
		SimpleGraph G=new SimpleGraph(0,E);
		this.G=G;
		this.vertexes.clear();
		this.edges.clear();
		this.counter=0;
		this.setOpaque(false) ;
		this.repaint();
	}
	
}
