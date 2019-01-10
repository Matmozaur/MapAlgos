package model.settings;

import java.awt.*;
import java.io.Serializable;

public class Settings implements Serializable {
    private static final long serialVersionUID = -2121378365221L;
    /**
     * Maximal number of vertexes
     */
    public static final int n = 1000;

    public final Color vcolor = Color.BLACK;
    public final Color ecolor = Color.BLACK;
    public final int vdiam = 24;
    public  CurrentAction currently = CurrentAction.VERTEX;
    public  Type GraphType = Type.UNDEFINED;
    public  boolean visibility=false;
    public final Color colorEx = Color.RED;
    public final Color colorS = Color.BLUE;
    /**
     * time between displaying algorithms steps
     */
    public long t = 500;
}
