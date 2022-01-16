package nl.saxion.cds.graph;

import java.util.List;

public interface Graph {
    void addVertex(Vertex vertex);
    void removeVertex(String label);
    void addEdge(Vertex src, Vertex dest);
    void removeEdge(Edge edge);
    List<Vertex> getVertices();
    List<Edge> getEdges();
}
