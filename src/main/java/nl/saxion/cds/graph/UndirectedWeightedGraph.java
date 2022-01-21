package nl.saxion.cds.graph;

import java.util.*;

public class UndirectedWeightedGraph implements Graph {
    private final List<Vertex> vertices;
    private final List<Edge> edges;

    public UndirectedWeightedGraph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    @Override
    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    @Override
    public void removeVertex(String label) {

    }

    @Override
    public void addEdge(Vertex src, Vertex dest) {
        int weight = (int) (Math.abs(src.getAddress().getX() - dest.getAddress().getX())
                + Math.abs(src.getAddress().getY() - dest.getAddress().getY()));

        var edge = new Edge(src, dest, weight);
        this.edges.add(edge);
    }

    @Override
    public void removeEdge(Edge edge) {

    }

    public List<Vertex> getVertices() {
        return new ArrayList<>(this.vertices);
    }

    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }
}
