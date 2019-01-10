package controller.file_controller;

import javax.swing.*;
import java.io.File;

public class FileCommunicator {
    private MyFileReader FR;
    private MyFileWriter FW;

    public MyFileWriter getFW() {
        return FW;
    }

    public MyFileReader getFR() {
        return FR;
    }
    private static FileCommunicator f;
    private FileCommunicator(){
        FR=MyFileReader.getInstance();
        FW=MyFileWriter.getInstance();
    }

    public static FileCommunicator getInstance(){
        if(f==null){
            f=new FileCommunicator();
        }
        return f;
    }


}
