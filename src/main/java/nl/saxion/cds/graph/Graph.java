package nl.saxion.cds.graph;

public interface Graph<T> {
    void addVertex(T date);
    void removeVertex(Vertex<T> vertex);
    void addEdge(Vertex<T> src, Vertex<T> dest, int weight);
    void removeEdge(Edge<T> edge);
}
