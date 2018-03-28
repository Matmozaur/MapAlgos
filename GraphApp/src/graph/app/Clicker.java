package graph.app;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        if (Main.currently == Now.VERTEX) {
            panel.addVertex(new Vertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2, panel.counter, null));
        }

        if (Main.currently == Now.EDGE) {
            if (j == 0) {
                flow = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
                if (flow != null) j = 1;
            } else {
                Vertex a = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
                if (a != null) {
                    Edge c = new Edge(a, flow, null);
                    panel.addEdge(c);
                    j--;
                }
            }
        }

        // new
        if (Main.currently == Now.WEIGHTEDGE) {
            if (j == 0) {
                flow = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
                if (flow != null) j = 1;
            } else {
                Vertex a = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
                if (a != null&&a!=flow) {
                    // i tak ta waga nie ma znaczenia
                   /*
                    Random rand = new Random();
                    int weight = rand.nextInt(11);
					*/
                	int weight=Integer.parseInt(JOptionPane.showInputDialog("Podaj wagê:"));
                    WeightEdge we = new WeightEdge(a, flow, null, weight);
                    panel.addWeightEdge(we);
                    j--;
                }
            }
        }

        if (Main.currently != Now.EDGE && Main.currently != Now.WEIGHTEDGE && Main.currently != Now.SIMPLEWEIGHT) j = 0;

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
                    WeightEdge w = panel.getWeightEdge(a, flow);
                    if (w != null) {
                        panel.removeWeightEdge(w);
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
                    //Edge c=new Edge(a,flow,null);
                    /*
                	PatchSet PS = panel.G.shortestPatches(a.getNumb(), flow.getNumb());
                    panel.G.colorPatch(PS.P[0], PS.length, panel);
                    */
                	int P[]=panel.G.shortestPatch(a.getNumb(),flow.getNumb());
                	panel.G.colorPatch(P, panel);
                    p--;
                }
            }
        }
        if (Main.currently != Now.PATCH) p = 0;
        
        if (Main.currently == Now.SIMPLEWEIGHT) {
            if (j == 0) {
                flow = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
                if (flow != null) j = 1;
            } else {
                Vertex a = panel.getVertex(e.getX() - Main.diam / 2, e.getY() - Main.diam / 2);
                if (a != null&&a!=flow) {
                    // i tak ta waga nie ma znaczenia
                   /*
                    Random rand = new Random();
                    int weight = rand.nextInt(11);
					*/
                	int weight=Integer.parseInt(JOptionPane.showInputDialog("Podaj wagê:"));
                    SimpleWeightEdge we = new SimpleWeightEdge(a, flow, null, weight);
                    panel.addSimpleWeightEdge(we);
                    j--;
                }
            }
        }


    }
}
