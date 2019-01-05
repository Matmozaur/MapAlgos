package controller.model_manipulation;

import model.settings.CurrentAction;
import model.elements.Edge;
import model.elements.Vertex;
import model.elements.WeightEdge;
import view.GraphPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class ConstructionController {
    private final GraphPanel panel;
    private  int j = 0;
    private  int r = 0;

    public ConstructionController(GraphPanel panel) {
        this.panel = panel;
    }

    public void action(MouseEvent e){
        if (panel.getSettings().currently == CurrentAction.VERTEX) {
            panel.addVertex(new Vertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2, panel.getCounter(), null,panel.getSettings()));
        }
        //EDGE
        if (panel.getSettings().currently == CurrentAction.EDGE) {
            if (j == 0) {
                panel.setCurrentVertex(panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2,
                        e.getY() - panel.getSettings().vdiam/ 2));
                if (panel.getCurrentVertex() != null) j = 1;
            }
            else {
                Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2);
                if (a != null) {
                    if(a != panel.getCurrentVertex()) {
                        Edge c = new Edge(a, panel.getCurrentVertex(), panel.getSettings());
                        panel.addEdge(c);
                        panel.unselect(a);
                        panel.setCurrentVertex(null);
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
        if (panel.getSettings().currently == CurrentAction.SIMPLEWEIGHT) {
            if (j == 0) {
                panel.setCurrentVertex(panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2,
                        e.getY() - panel.getSettings().vdiam/ 2));
                if (panel.getCurrentVertex() != null) j = 1;
            }
            else {
                Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2);
                if (a != null && a != panel.getCurrentVertex()) {
                    if(panel.getEdge(a, panel.getCurrentVertex()) == null) {
                        String response;
                        int weight;
                        boolean leave = false;
                        while(!leave) {
                            try {
                                response = JOptionPane.showInputDialog("Set weight:");
                                if (response.equals("")) {
                                    leave = true;
                                    // handling Cancel
                                    //throw new NumberFormatException();
                                } else {
                                    weight = Integer.parseInt(response);
                                    WeightEdge we = new WeightEdge(a, panel.getCurrentVertex(), null, weight,panel.getSettings());
                                    panel.addWeightEdge(we);
                                    j--;
                                    panel.setCurrentVertex(null);
                                }
                                leave = true;
                            } catch(NumberFormatException ex) {
                                ex.getLocalizedMessage();
                            } catch (NullPointerException ex2) {
                                // handling Cancel
                                panel.unselect(a);
                                panel.setCurrentVertex(null);
                                j = 0;
                                leave = true;
                            }
                        }
                    }
                    else {
                        panel.unselect(a);
                        panel.setCurrentVertex(null);
                        j = 0;
                        JOptionPane.showMessageDialog(panel, "This edge already exist!");
                    }
                }
                else {
                    if(a==panel.getCurrentVertex()) panel.setCurrentVertex(null);
                    j--;
                }
            }
        }
        if (panel.getSettings().currently != CurrentAction.EDGE && panel.getSettings().currently != CurrentAction.SIMPLEWEIGHT) j = 0;

                if (panel.getSettings().currently == CurrentAction.VERTEXREMOVE) {
            Vertex v = panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2);
            if (v != null) {
                panel.removeVertex(v);
            }
        }

        if (panel.getSettings().currently == CurrentAction.EDGEREMOVE) {
            if (r == 0) {
                panel.setCurrentVertex(panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2));
                if (panel.getCurrentVertex() != null) r = 1;
            } else {
                Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2);
                if (a != null) {
                    Edge c = panel.getEdge(a, panel.getCurrentVertex());
                    if (c != null) {
                        panel.removeEdge(c);
                        r--;
                    }
                    Edge w = panel.getEdge(a, panel.getCurrentVertex());
                    if (w != null) {
                        panel.removeEdge(w);
                        r--;
                    }
                }
            }
        }

        if (panel.getSettings().currently == CurrentAction.CHANGELABEL) {
            Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2);
            if (a != null) {
                String response;
                boolean leave = false;
                while(!leave) {
                    try {
                        response = JOptionPane.showInputDialog("Set label:");
                        if (response !=null) {
                            if(!response.equals("")){
                                a.setLabel(response);
                            }
                        }
                    }
                    catch (NullPointerException ex2) {
                        // handling Cancel
                        ex2.printStackTrace();
                    }
                    finally {
                        panel.unselect(a);
                        panel.setCurrentVertex(null);
                        j = 0;
                        leave = true;
                    }
                }
            }
            panel.setCurrentVertex(null);
            j=0;
        }


        if (panel.getSettings().currently != CurrentAction.EDGEREMOVE) r = 0;

    }
}
