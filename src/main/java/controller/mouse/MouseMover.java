package controller.mouse;

import view.GraphPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMover implements MouseMotionListener
{
    private final GraphPanel panel;

    public MouseMover(GraphPanel panel) {
        super();
        this.panel = panel;
    }

    public void mouseMoved(MouseEvent e)
    {

        //if (panel.getVertex(e.getX() - GraphApp.getDiam()/ 2, e.getY() - GraphApp.getDiam()/ 2)== null) panel.setCursor(Cursor.getDefaultCursor());
        //else panel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    public void mouseDragged(MouseEvent event)
    {
        if (panel.getCurrentVertex() != null)
        {
            int x = event.getX();
            int y = event.getY();

            // drag the current rectangle to center it at (x, y)
            panel.getCurrentVertex().setX(x - panel.getSettings().vdiam/ 2);
            panel.getCurrentVertex().setY(y - panel.getSettings().vdiam/ 2);
            panel.setOpaque(false);
            panel.repaint();
        }
    }
}