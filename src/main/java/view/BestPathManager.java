package view;

import controller.element_controller.ElementController;
import model.elements.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BestPathManager extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton maximalNumberOfVerticesRadioButton;
    private JRadioButton maximalSummaryWeightWithoutRadioButton;
    private JSpinner maxWeight;
    ElementController EC;
    Vertex u;
    Vertex v;


    public BestPathManager(ElementController EC, Vertex u,Vertex v) {
        setTitle("Generator");
        setBounds(300, 300, 600, 150);
        setLocationRelativeTo(null);
        this.EC=EC;
        this.u=u;
        this.v=v;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        maximalNumberOfVerticesRadioButton.setSelected(true);
        maximalNumberOfVerticesRadioButton.addActionListener(e -> {
            maximalSummaryWeightWithoutRadioButton.setSelected(false);
            maxWeight.setEnabled(true);
        });
        maximalSummaryWeightWithoutRadioButton.setSelected(false);
        maximalSummaryWeightWithoutRadioButton.addActionListener(e -> {
            maximalNumberOfVerticesRadioButton.setSelected(false);
            maxWeight.setEnabled(false);

        });
        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());


    }

    private void onOK() {
        if(maximalSummaryWeightWithoutRadioButton.isSelected()){
            EC.visualizeMaxWeightPath(u,v);
        }
        if(maximalNumberOfVerticesRadioButton.isSelected()){
            EC.visualizeMaxVerticesPath(u,v,(Integer) maxWeight.getValue());
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }


}
