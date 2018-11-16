package model;


import view.GraphPanel;
import representations.Edge;
import representations.SimpleWeightEdge;
import representations.Vertex;


import java.awt.Color;

/**
 * Abstract class used to represent various types of model
 * @author Matmozaur
 *
 */
public abstract class Graph {
    /**
     * Maximal number of vertexes
     */
    public static final int n = 100;
    public int last = -1;
    public static Color color = Color.RED;
    /**
     * time between displaying algorithms steps
     */
    public static long t = 500;
    /**
     * Order
     */
    public int V;


    public Graph(int v) {
        V = v;
    }

    /**
     * swaps 2 elements
     *
     * @param A
     * @param B
     */
    public static void swap(int A[], int B[]) {
        int C[] = A;
        A = B;
        B = C;
    }

    /**
     * gives vertex a from GraphPanel panel color from Graph class
     *
     * @param a
     * @param panel
     */
    void color(int a, GraphPanel panel) {
        for (Vertex c : panel.vertexes) {
            if (c.getNumb() == a) {
                c.setColor(color);
                panel.update(panel.getGraphics());
            }
        }
    }

    /**
     * gives edge  from a to b from GraphPanel panel color from Graph class
     *
     * @param a
     * @param b
     * @param panel
     */
    void color(int a, int b, GraphPanel panel) {
        for (Edge c : panel.edges) {
            if ((c.a.getNumb() == a && c.b.getNumb() == b) || (c.b.getNumb() == a && c.a.getNumb() == b)) {
                c.setColor(color);
                panel.update(panel.getGraphics());
            }
        }
        for (SimpleWeightEdge c : panel.simpleW) {
            if ((c.a.getNumb() == a && c.b.getNumb() == b) || (c.b.getNumb() == a && c.a.getNumb() == b)) {
                c.setColor(color);
                panel.update(panel.getGraphics());
            }
        }
    }
}
