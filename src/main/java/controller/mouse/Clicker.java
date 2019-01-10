package controller.mouse;

import controller.element_controller.ElementController;
import controller.info_controller.InfoController;
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
    private final InfoController IC;
    private final ElementController EC;

    public Clicker(GraphPanel panel) {
        super();
        this.panel = panel;
        CC=new ConstructionController(panel);
        AC=new AlgorithmVisualisationController(panel);
        IC=new InfoController(panel);
        EC=new ElementController(panel);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (CurrentAction.getGraphElements().contains(panel.getSettings().currently)) {
            CC.action(e);
        }
        if(CurrentAction.getAlgorithms().contains(panel.getSettings().currently)) {
            AC.action(e);
        }
        if(CurrentAction.getInfo().contains(panel.getSettings().currently)) {
            IC.action(e);
        }
        if(CurrentAction.getElements().contains(panel.getSettings().currently)) {
            EC.action(e);
        }

    }

    public ElementController getEC() {
        return EC;
    }
}

