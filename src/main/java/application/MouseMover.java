package application;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMover implements MouseMotionListener
{
   private GraphPanel panel;
   
   public MouseMover(GraphPanel panel) {
       super();
       this.panel = panel;
   }
   
   public void mouseMoved(MouseEvent e)
   {
      
      //if (panel.getVertex(e.getX() - Main.getDiam()/ 2, e.getY() - Main.getDiam()/ 2)== null) panel.setCursor(Cursor.getDefaultCursor());
      //else panel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
   }

   public void mouseDragged(MouseEvent event)
   {
      if (panel.currentVertex != null)
      {
         int x = event.getX();
         int y = event.getY();

         // drag the current rectangle to center it at (x, y)
         panel.currentVertex.setX(x - Main.getDiam()/ 2);
         panel.currentVertex.setY(y - Main.getDiam()/ 2);
         panel.setOpaque(false);
         panel.repaint();
      }
   }
}   
