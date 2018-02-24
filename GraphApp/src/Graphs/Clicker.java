package Graphs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Clicker extends MouseAdapter {
	private GraphPanel panel;
	private static int j=0;
	private Vertex flow;
	 
	public Clicker(GraphPanel panel) {
		super();
		this.panel=panel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(Main.actually==Now.VERTEX) {
			panel.counter++;
			panel.addVertex(new Vertex(e.getX()-Main.diam/2,e.getY()-Main.diam/2,panel.counter,null));
		}
		
		if(Main.actually==Now.EDGE) {
			if(j==0) {
				flow=panel.getVertex(e.getX()-Main.diam/2,e.getY()-Main.diam/2);
				if(flow!=null) j=1;
			}
			else {
				Vertex a=panel.getVertex(e.getX()-Main.diam/2,e.getY()-Main.diam/2);
				if(a!=null) {
					Edge c=new Edge(a,flow,null);
					panel.addEdge(c);
					j--;
				}
			}
		}
		
		if(Main.actually==Now.DFS) {
			Vertex a=panel.getVertex(e.getX()-Main.diam/2,e.getY()-Main.diam/2);
			if(a!=null) {
				panel.G.DeepSearch(panel.G,panel);
			}
		}
	}
}
