package view;

import controller.Main;
import javax.swing.*;


public class MainFrame extends JFrame {

    private JButton newMapManagerButton;
    private JButton loadSavedMapButton;
    private JButton documentationButton;
    private JButton helpButton;
    private JButton exitButton;
    private JPanel MainPanel;
    private JLabel Label;

    public MainFrame() {
        initialize();
    }

    private void initialize(){
        setTitle("MapApp");
        setBounds(100, 100, 200, 300);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(MainPanel);
        newMapManagerButton.addActionListener(actionEvent -> Main.newManager());
        loadSavedMapButton.addActionListener(actionEvent -> {
        });
        documentationButton.addActionListener(actionEvent -> {
        });
        helpButton.addActionListener(actionEvent -> {
        });
        exitButton.addActionListener(actionEvent -> System.exit(0));

    }
}
