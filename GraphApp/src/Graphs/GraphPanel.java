package Graphs;
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
	
	
	public  Vertex getVertex(int u,int v) {
		for(Vertex c:vertexes) {
			if(c.getX()<=u+Main.diam&&c.getX()>=u-Main.diam&&c.getY()<=v+Main.diam&&c.getY()>=v-Main.diam) {
				return c;
			}
		}
		return null;
	}

	
	public void addVertex(Vertex c) {
		if(getVertex(c.getX(),c.getY())==null) {
		vertexes.add(c);
		this.update(this.getGraphics());
		G.V++;
		}
	}
	
	public void addEdge(Edge c) {
		edges.add(c);
		this.update(this.getGraphics());
		G.E[c.a.getNumb()][c.b.getNumb()]=G.E[c.b.getNumb()][c.a.getNumb()]=true;
	}
	
}
