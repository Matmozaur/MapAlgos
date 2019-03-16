package controller;

import controller.file_controller.FileCommunicator;
import model.graphs.representation.WeightGraph;
import view.GraphApp;
import view.MainFrame;

import java.awt.*;

/**
 * Main class for starting application
 * 
 */
public class Main {
    private static final FileCommunicator FC=FileCommunicator.getInstance();
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainFrame main=new MainFrame();
            main.setVisible(true);
        });
    }

    public static void newManager(){

        Thread t=new Thread(() -> {
            try {
                GraphApp window = new GraphApp();
                window.getFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t.start();
    }

    public static void loadManager(WeightGraph G){

        Thread t=new Thread(() -> {
            try {
                GraphApp window = new GraphApp(G);
                window.getFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t.start();
    }

    public static FileCommunicator getFC() {
        return FC;
    }
}
