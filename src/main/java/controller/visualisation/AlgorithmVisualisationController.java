package controller.visualisation;
import model.settings.CurrentAction;
import model.elements.Vertex;
import view.GraphPanel;

import java.awt.event.MouseEvent;

public class AlgorithmVisualisationController {

    private final GraphPanel panel;
    private final SlowPainter slowpainter;

    public AlgorithmVisualisationController(GraphPanel panel) {
        this.panel = panel;
        slowpainter=new SlowPainter(panel.getG(),panel);
    }

    public void action(MouseEvent e){

       if (panel.getSettings().currently == CurrentAction.DFS) {
            Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2);
            if (a != null) {
                Thread t=new Thread(()->{
                    try {
                        panel.getG().getLogic().vDFS(a.getNumb(),slowpainter);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    finally {
                        panel.getSettings().currently=CurrentAction.VERTEX;
                    }
                });
                t.start();
            }
       }
        if (panel.getSettings().currently == CurrentAction.DEEPSEARCH) {
            Thread t=new Thread(()-> {
                try {
                    panel.getG().getLogic().vDeepSearch(slowpainter);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } finally {
                    panel.getSettings().currently = CurrentAction.VERTEX;
                }
            });
            t.start();
        }

        if (panel.getSettings().currently == CurrentAction.BFS) {
            Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2);
            if (a != null) {
                Thread t=new Thread(()-> {
                    try {
                        panel.getG().getLogic().vBFS(a.getNumb(), slowpainter);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } finally {
                        panel.getSettings().currently = CurrentAction.VERTEX;
                    }
                });
                t.start();
            }
        }

        if (panel.getSettings().currently == CurrentAction.BREADTHSEARCH) {
            Thread t=new Thread(()-> {
                try {
                    panel.getG().getLogic().vBreadthSearch(slowpainter);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } finally {
                    panel.getSettings().currently = CurrentAction.VERTEX;
                }
            });
            t.start();
        }

        if (panel.getSettings().currently == CurrentAction.KRASKAL) {
            Thread t=new Thread(()-> {
                try {
                    panel.getG().getLogic().vkraskal(slowpainter);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } finally {
                    panel.getSettings().currently = CurrentAction.VERTEX;
                }
            });
            t.start();
        }

        if(panel.getSettings().currently == CurrentAction.PRIM) {
            Vertex start = panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2);
            if(start != null) {
                Thread t=new Thread(()-> {
                    try {
                        panel.getG().getLogic().vprim(slowpainter, start);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });
                t.start();
            }

        }

        if(panel.getSettings().currently == CurrentAction.DIJKSTRA) {
            Vertex start = panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2);
            if(start != null) {
                Thread t=new Thread(()-> {
                    try {
                        panel.getG().getLogic().dijkstra(start, slowpainter);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });
                t.start();
            }
        }










//
//        if (Settings.() == CurrentAction.PATCH) {
//            if (p == 0) {
//                panel.getCurrentVertex() = panel.getVertex(e.getX() - Settings.vdiam/ 2, e.getY() - Settings.vdiam/ 2);
//                if (panel.getCurrentVertex() != null) p = 1;
//            } else {
//                Vertex a = panel.getVertex(e.getX() - Settings.vdiam/ 2, e.getY() - Settings.vdiam/ 2);
//                if (a != null&&a!=panel.getCurrentVertex()) {
//                    int P[]=panel.G.shortestPatch(a.getNumb(),panel.getCurrentVertex().getNumb());
//                    panel.G.colorPatch(P, panel);
//                    p--;
//                }
//            }
//        }
//        if (Settings.() != CurrentAction.PATCH) p = 0;
//
//        if (Settings.() == CurrentAction.ECCENTRICY) {
//            Vertex a = panel.getVertex(e.getX() - Settings.vdiam / 2, e.getY() - Settings.vdiam / 2);
//            if(a!=null) {
//                int eccentricy = panel.G.eccentricyOfVertex(a.getNumb());
//                JOptionPane.showMessageDialog(panel,"Vertexex eccenticity is equals: "+eccentricy);
//                panel.unselect(a);
//            }
//        }
//
//        if (Settings.() == CurrentAction.DEGREE) {
//            Vertex a = panel.getVertex(e.getX() - Settings.vdiam / 2, e.getY() - Settings.vdiam / 2);
//            if(a!=null) {
//                int degree = panel.G.degreeOfVertex(a.getNumb());
//                JOptionPane.showMessageDialog(panel,"Vertexes degree is equal: "+degree);
//                panel.unselect(a);
//            }
//        }

    }
}
