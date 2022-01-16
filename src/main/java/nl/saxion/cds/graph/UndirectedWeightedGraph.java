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
//
//                var edge = new Edge(src, dest, weight);
//        this.adjVertices.get(src).add(edge);
//        this.adjVertices.get(dest).add(edge);
//        edges.add(edge);
    }

    @Override
    public void removeEdge(Edge edge) {

    }

    private boolean allNodesVisited() {
        for (var vertex :
                vertices) {
            if (!vertex.isVisited()) {
                return false;
            }
        }

        return true;
    }

    public List<Vertex> getVertices() {
//        return this.adjVertices.keySet();
        return new ArrayList<>(this.vertices);
    }

    public List<Edge> getEdges() {
        var src = this.vertices.get(0);
        src.setVisited(true);

        while (!allNodesVisited()) {
            int nextMinimum = Integer.MAX_VALUE;
            int min = Integer.MAX_VALUE;
            var dest = src;
            for (var vertex : this.vertices) {
                if (vertex.isVisited()) {
                    continue;
                }

                var weight = Math.abs(src.getAddress().getX() - vertex.getAddress().getX())
                        + Math.abs(src.getAddress().getY() - vertex.getAddress().getY());

                if (weight < nextMinimum) {
                    nextMinimum = (int)weight;
                    dest = vertex;
                }

            }
            dest.setVisited(true);
            this.edges.add(new Edge(src, dest, nextMinimum));
            src = dest;
        }

        return this.edges;
    }
}
