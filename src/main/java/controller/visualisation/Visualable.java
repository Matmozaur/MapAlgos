package controller.visualisation;

public interface Visualable {
    void visualVertex(int v) throws InterruptedException;
    void visualEdge(int a, int b) throws InterruptedException;

}
