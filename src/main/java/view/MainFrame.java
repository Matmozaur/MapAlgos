package view;

import controller.Main;
import model.graphs.representation.WeightGraph;

import javax.swing.*;
import java.net.URL;


public class MainFrame extends JFrame {

    private JButton newMapManagerButton;
    private JButton loadSavedMapButton;
    private JButton generateButton;
    private JButton helpButton;
    private JButton exitButton;
    private JPanel MainPanel;
    private JLabel Label;

    public MainFrame() {
        initialize();
    }

    private void initialize(){
        setTitle("MapAlgos");
        setBounds(300, 300, 300, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(MainPanel);
        newMapManagerButton.addActionListener(actionEvent -> Main.newManager());
        loadSavedMapButton.addActionListener(actionEvent ->{
            WeightGraph G=Main.getFC().getFR().getGraph(this);
            if(G!=null){
                Main.loadManager(G);
            }
        });
        generateButton.addActionListener(actionEvent -> {
            GraphGenerator generator=new GraphGenerator();
            generator.setVisible(true);
        });
        helpButton.addActionListener(actionEvent -> {
            URL helpFile=Main.class.getResource("/Help.html");
            HelpWindow n=new HelpWindow(helpFile);
            n.setVisible(true);
        });

        exitButton.addActionListener(actionEvent -> System.exit(0));

    }
}
