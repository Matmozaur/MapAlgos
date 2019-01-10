package controller.info_controller;

import model.elements.Vertex;
import model.settings.CurrentAction;
import view.GraphPanel;

import java.awt.event.MouseEvent;

public class InfoController {
    private final GraphPanel panel;
    private int flag=0;

    public InfoController(GraphPanel panel){
        this.panel=panel;
    }

    public void action(MouseEvent e) {
        if (panel.getSettings().currently == CurrentAction.VINFO) {
            Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam / 2, e.getY() - panel.getSettings().vdiam / 2);
            if (a != null) {
                panel.getMyParent().getInfo().setText("Info: \n" + panel.getG().vertexInfo(a));
            }
            flag=0;
        }
        if (panel.getSettings().currently == CurrentAction.CONNECTED) {
            Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam / 2, e.getY() - panel.getSettings().vdiam / 2);
            if (a != null&&!a.equals(panel.getCurrentVertex())) {
               if(flag==0){
                   flag++;
                   panel.setCurrentVertex(a);
               }
               else{
                   flag=0;
                   if(panel.getG().connected(a,panel.getCurrentVertex())){
                       panel.getMyParent().getInfo().setText("Vertices connected");
                   }
                   else{
                       panel.getMyParent().getInfo().setText("Vertices not connected");
                   }
                   panel.setCurrentVertex(null);
               }
            }
        }
    }

}
