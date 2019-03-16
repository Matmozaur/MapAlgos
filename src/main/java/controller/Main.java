package controller;

import view.GraphApp;
import view.MainFrame;

import java.awt.*;

/**
 * Main class for starting application
 */
public class Main {
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
}
