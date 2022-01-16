package nl.saxion.cds.graph;

import java.util.*;

public class UndirectedWeightedGraph implements Graph {

//    private Map<Vertex, List<Edge>> adjVertices;

    private List<Vertex> vertices;
    private List<Edge> edges;

    public UndirectedWeightedGraph() {
//        this.adjVertices = new HashMap<>();
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    @Override
    public void addVertex(Vertex vertex) {
//        adjVertices.putIfAbsent(vertex, new ArrayList<>());
        vertices.add(vertex);
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
//        this.adjVertices.get(src).add(edge);
//        this.adjVertices.get(dest).add(edge);
        edges.add(edge);
    }

    @Override
    public void removeEdge(Edge edge) {

    }

    public List<Vertex> getVertices() {
//        return this.adjVertices.keySet();
        return new ArrayList<>(this.vertices);
    }

    public List<Edge> getEdges() {
//        return adjVertices.values();
        return new ArrayList<>(this.edges);

    }
}
