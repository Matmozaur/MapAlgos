package model.graphs.logic;

import model.graphs.representation.WeightGraph;
import model.settings.Settings;

import java.io.Serializable;

/**
 * Abstract class used to represent various types of model
 * @author Matmozaur
 *
 */
public abstract class GraphLogic implements Serializable {
    private static final long serialVersionUID = -15674135734535221L;
    /**
     * Order
     */
    int V;
    int last = -1;
    final WeightGraph parent;
    final int n= Settings.n;


    GraphLogic(WeightGraph parent, int v) {
        this.V = v;
        this.parent=parent;
    }

    public void addV(){
        V++;
    }

    public int getV(){
        return this.V;
    }




}

