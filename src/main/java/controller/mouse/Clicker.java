package controller.mouse;

import controller.model_manipulation.ConstructionController;
import controller.visualisation.AlgorithmVisualisationController;
import model.settings.CurrentAction;
import view.GraphPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Clicker extends MouseAdapter {
    private final GraphPanel panel;
    private final ConstructionController CC;
    private final AlgorithmVisualisationController AC;

    public Clicker(GraphPanel panel) {
        super();
        this.panel = panel;
        CC=new ConstructionController(panel);
        AC=new AlgorithmVisualisationController(panel);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (CurrentAction.getGraphElements().contains(panel.getSettings().currently)) {
            CC.action(e);
        }
        if(CurrentAction.getAlgorithms().contains(panel.getSettings().currently)) {
            AC.action(e);
        }


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

