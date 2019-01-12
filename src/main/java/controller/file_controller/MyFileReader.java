package controller.file_controller;

import model.graphs.representation.WeightGraph;

import javax.swing.*;
import java.io.*;


public class MyFileReader {
    private final JFileChooser FileChooser;
    private static MyFileReader f;
    private MyFileReader(){
        FileChooser=new JFileChooser();
    }
    public static MyFileReader getInstance(){
        if(f==null){
            f=new MyFileReader();
        }
        return f;
    }

    private File getUsersFile(JFrame parent){
        int flag=FileChooser.showOpenDialog(parent);
        if(flag==JFileChooser.APPROVE_OPTION){
            File file=FileChooser.getSelectedFile();
            return file;
        }
        return null;
    }

    public WeightGraph getGraph(JFrame parent){
        try {
            File file=getUsersFile(parent);
            if(file!=null) {
                FileInputStream fileStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileStream);
                WeightGraph G = (WeightGraph) in.readObject();
                in.close();
                fileStream.close();
                return G;
            }
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
