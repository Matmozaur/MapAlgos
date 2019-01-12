package controller.file_controller;

import model.graphs.representation.WeightGraph;

import javax.swing.*;
import java.io.*;


public class MyFileWriter {
    private final JFileChooser FileChooser;
    private static MyFileWriter f;
    private MyFileWriter(){
        FileChooser=new JFileChooser();
    }
    public static MyFileWriter getInstance(){
        if(f==null){
            f=new MyFileWriter();
        }
        return f;
    }

    private void save(File file, WeightGraph w){

        try {
            FileOutputStream fileStream = new FileOutputStream(file);
            try {
                ObjectOutputStream out = new ObjectOutputStream(fileStream);
                out.writeObject(w);
                out.close();
                fileStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                }
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void saveAs(WeightGraph w,JFrame parent) {
        FileWriter Writer = null;
        File file;
        int flag = FileChooser.showOpenDialog(parent);
        if (flag == JFileChooser.APPROVE_OPTION) file = FileChooser.getSelectedFile();
        else return;
        save(file,w);
    }
}
