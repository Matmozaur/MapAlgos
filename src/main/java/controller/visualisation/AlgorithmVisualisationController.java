package controller.visualisation;
import controller.visualisation.painters.LabelPainter;
import controller.visualisation.painters.SimplePainter;
import model.settings.CurrentAction;
import model.elements.Vertex;
import view.GraphPanel;

import java.awt.event.MouseEvent;

public class AlgorithmVisualisationController {

    private final GraphPanel panel;
    private SimplePainter slowpainter;
    private LabelPainter labelPainter;

    public AlgorithmVisualisationController(GraphPanel panel) {
        this.panel = panel;
        slowpainter=new SimplePainter(panel.getG(),panel);
        labelPainter=new LabelPainter(panel.getG(),panel);
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
                        panel.getG().getLogic().vprim(slowpainter, start.getNumb());
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
                        panel.getG().getLogic().vdijkstra(start, labelPainter);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });
                t.start();
            }
        }


    }
}
