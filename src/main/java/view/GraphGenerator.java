package view;


import controller.Main;
import controller.model_manipulation.GenerationController;
import model.additional.Algos;
import model.elements.Edge;
import model.elements.Vertex;
import model.graphs.representation.WeightGraph;
import model.settings.Settings;

import javax.swing.*;


public class GraphGenerator extends JFrame{
    private JList list;
    private JButton generateButton;
    private JLabel Vnumb;
    private JPanel panel;
    private JSpinner numberV;

    GraphGenerator(){
        initialize();
    }

    private void initialize(){
        setTitle("Generator");
        setBounds(300, 300, 400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        add(panel);
        SpinnerModel model = new SpinnerNumberModel(0, 0, 100, 1);
        numberV.setModel(model);
        generateButton.addActionListener(actionEvent -> {
            if(list.getSelectedValue()!=null){
                generate(list.getSelectedValue().toString(),(Integer) numberV.getValue());
            }
        });
    }

    private void generate(String type,int v){
        WeightGraph G;
        switch (type){
            case "Full": G= GenerationController.generateFull(v); break;
            case "Path": G=GenerationController.generatePath(v); break;
            case "Cycle": G=GenerationController.generateCycle(v); break;
            case "Tusk": G=GenerationController.generateTusk(v); break;
            case "Binary Tree": G=GenerationController.generateBT(v); break;
            default: return;
        }
        if(G!=null){
            Main.loadManager(G);
            this.setVisible(false);
        }

    }



}
