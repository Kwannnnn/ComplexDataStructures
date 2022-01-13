package nl.saxion.cds.graph;

public class Edge<T> {
    private Vertex<T> src;
    private Vertex<T> dest;
    private int weight;

    public Edge(Vertex<T> src, Vertex<T> dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}
