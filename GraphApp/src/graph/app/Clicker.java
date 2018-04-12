package graph.app;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Random;

import javax.swing.JOptionPane;

public class Clicker extends MouseAdapter {
    private GraphPanel panel;
    private static int j = 0;
    private static int r = 0;
    private static int p = 0;
    private Vertex flow;

    public Vertex getFlow() {
        return flow;
    }

    public void setFlow(Vertex flow) {
        this.flow = flow;
    }

    public Clicker(GraphPanel panel) {
        super();
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    	//VERTEX
        if (Main.currently == Now.VERTEX) {
            panel.addVertex(new Vertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2, panel.counter, null));
        }
        
        
        //EDGE
        if (Main.currently == Now.EDGE) {
            if (j == 0) {
                flow = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
                if (flow != null) j = 1;
            } 
            else {
                Vertex a = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
                if (a != null) {
                	if(a != flow) {
                		Edge c = new Edge(a, flow, null);
                        panel.addEdge(c);
                        panel.unselect(a);
                        panel.unselect(flow);
                        j--;
                	}
                	else {
                		j--;
                		panel.unselect(a);
                	}
                }
            }
        }
        
        //SIMPLE WEIGHT EDGE
        if (Main.currently == Now.SIMPLEWEIGHT) {
            if (j == 0) {
                flow = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
                if (flow != null) j = 1;
            } else {
                Vertex a = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
                if (a != null && a != flow) {
<<<<<<< HEAD:GraphApp/src/graph/app/Clicker.java
                    if(panel.getSimpleWeightEdge(a, flow) == null) {
                    	String response;
	                    int weight;
	                    boolean leave = false;
	                    while(leave == false) {
	                        try {
	                            response = JOptionPane.showInputDialog("Podaj wagę:");
	                            if (response.equals("")) {
	                                leave = true;
	                                // handling Cancel
	                                //throw new NumberFormatException();
	                            } else {
	                                weight = Integer.parseInt(response);
	                                SimpleWeightEdge we = new SimpleWeightEdge(a, flow, null, weight);
	                                panel.addSimpleWeightEdge(we);
	                                j--;
	                                panel.unselect(a, flow);
	                            }
	                            leave = true;
	                        } catch(NumberFormatException ex) {
	                            ex.getLocalizedMessage();
	                        } catch (NullPointerException ex2) {
	                            // handling Cancel
	                            panel.unselect(a, flow);
	                            j = 0;
	                            leave = true;
	                        }
	                    }
                    }
                    else {
                    	panel.unselect(a, flow);
                    	flow=null;
                        j = 0;
                        JOptionPane.showMessageDialog(panel, "Juz istnieje taka kraw�d!");
=======
                    String response;
                    int weight;
                    boolean leave = false;
                    while(leave == false) {
                        try {
                            response = JOptionPane.showInputDialog("Podaj wagÄ™:");
                            if (response.equals("")) {
                                leave = true;
                                // handling Cancel
                                //throw new NumberFormatException();
                            } else {
                                weight = Integer.parseInt(response);
                                SimpleWeightEdge we = new SimpleWeightEdge(a, flow, null, weight);
                                panel.addSimpleWeightEdge(we);
                                j--;
                                panel.unselect(a, flow);
                            }
                            leave = true;
                        } catch(NumberFormatException ex) {
                            ex.getLocalizedMessage();
                        } catch (NullPointerException ex2) {
                            // handling Cancel
                            panel.unselect(a, flow);
                            j = 0;
                            leave = true;
                        }
>>>>>>> 43a66a16ffb789e5dca20ef1619c6c972b21dab7:GraphApp.2.2/src/graph/app/Clicker.java
                    }
                }
                else {
                	if(a==flow) panel.unselect(a);
                	j--;
                }
            }
        }
        if (Main.currently != Now.EDGE && Main.currently != Now.SIMPLEWEIGHT) j = 0;

        if (Main.currently == Now.DFS) {
            Vertex a = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
            if (a != null) {
                panel.G.DFS(panel.G, a.getNumb(), panel);
            }
        }

        if (Main.currently == Now.DEEPSEARCH) {
            panel.G.DeepSearch(panel.G, panel);
        }
        if (Main.currently == Now.KRASKAL) {
            WeightGraph.kraskal(panel.G, panel);
        }

        if(Main.currently == Now.PRIM) {
            Vertex start = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
            if(start != null) {
                WeightGraph.prim(panel, start);
            }
        }
        
        if (Main.currently == Now.BFS) {
            Vertex a = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
            if (a != null) {
                panel.G.BFS(panel.G, a.getNumb(), panel);
            }
        }

        if (Main.currently == Now.BREADTHSEARCH) {
            panel.G.BreadthSearch(panel.G, panel);
        }

        if (Main.currently == Now.VERTEXREMOVE) {
            Vertex v = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
            if (v != null) {
                panel.removeVertex(v);
            }
        }

        if (Main.currently == Now.EDGEREMOVE) {
            if (r == 0) {
                flow = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
                if (flow != null) r = 1;
            } else {
                Vertex a = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
                if (a != null) {
                    Edge c = panel.getEdge(a, flow);
                    if (c != null) {
                        panel.removeEdge(c);
                        r--;
                    }
                    SimpleWeightEdge w = panel.getSimpleWeightEdge(a, flow);
                    if (w != null) {
                        panel.removeSimpleWeightEdge(w);
                        r--;
                    }
                }
            }
        }
        if (Main.currently != Now.EDGEREMOVE) r = 0;

        if (Main.currently == Now.PATCH) {
            if (p == 0) {
                flow = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
                if (flow != null) p = 1;
            } else {
                Vertex a = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
                if (a != null&&a!=flow) {
                	int P[]=panel.G.shortestPatch(a.getNumb(),flow.getNumb());
                	panel.G.colorPatch(P, panel);
                    p--;
                }
            }
        }
        if (Main.currently != Now.PATCH) p = 0;
        
        if (Main.currently == Now.ECCENTRICY) {
        	Vertex a = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
        	if(a!=null) {
        		int eccentricy = panel.G.eccentricyOfVertex(a.getNumb());
        		JOptionPane.showMessageDialog(panel,"Ekscentrycznoœ wierzcho³ka jest równa "+eccentricy);
        		panel.unselect(a);
        	}
        }
        
        if (Main.currently == Now.DEGREE) {
        	Vertex a = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
        	if(a!=null) {
        		int degree = panel.G.degreeOfVertex(a.getNumb());
        		JOptionPane.showMessageDialog(panel,"Stopieñ wierzcho³ka jest równy "+degree);
        		panel.unselect(a);
        	}
        }
    }
}
