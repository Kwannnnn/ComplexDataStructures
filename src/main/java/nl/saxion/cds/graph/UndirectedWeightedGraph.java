package nl.saxion.cds.graph;

import java.util.*;

public class UndirectedWeightedGraph implements Graph {

    private Map<Vertex, List<Edge>> adjVertices;

    public UndirectedWeightedGraph() {
        this.adjVertices = new HashMap<>();
    }

    @Override
    public void addVertex(Vertex vertex) {
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void removeVertex(String label) {

    }

    @Override
    public void addEdge(Vertex src, Vertex dest) {
        var srcAddress = src.getAddress();
        var destAddress = dest.getAddress();

        int weight = (int) (Math.abs(srcAddress.getX() - destAddress.getX()) + Math.abs(srcAddress.getY() - destAddress.getY()));
        var edge = new Edge(src, dest, weight);
        this.adjVertices.get(src).add(edge);
        this.adjVertices.get(dest).add(edge);
    }

    @Override
    public void removeEdge(Edge edge) {

    }

    public Set<Vertex> getVertices() {
        return this.adjVertices.keySet();
    }

    public Collection<List<Edge>> getAdjVertices() {
        return adjVertices.values();
    }
}
