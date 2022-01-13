package nl.saxion.cds.graph;

public interface Graph {
    void addVertex(Vertex vertex);
    void removeVertex(String label);
    void addEdge(Vertex src, Vertex dest);
    void removeEdge(Edge edge);
}
