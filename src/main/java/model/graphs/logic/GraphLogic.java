package model.graphs.logic;

import model.graphs.representation.Graph;
import model.settings.Settings;

/**
 * Abstract class used to represent various types of model
 * @author Matmozaur
 *
 */
public abstract class GraphLogic {
    /**
     * Order
     */
    int V;
    int last = -1;
    final Graph parent;
    final int n= Settings.n;


    GraphLogic(Graph parent, int v) {
        this.V = v;
        this.parent=parent;
    }

    public void addV(){
        V++;
    }




}

