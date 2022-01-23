package nl.saxion.cds.graph.algorithms;

import nl.saxion.cds.graph.Edge;
import nl.saxion.cds.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

public class PrimAlgo {
    private final List<Vertex> vertices;
    private final List<Edge> edges = new ArrayList<>();

    public PrimAlgo(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Edge> execute() {
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
                    nextMinimum = (int) weight;
                    dest = vertex;
                }

            }
            dest.setVisited(true);
            this.edges.add(new Edge(src, dest, nextMinimum));
            src = dest;
        }

        return this.edges;
    }


    private boolean allNodesVisited() {
        for (var vertex : vertices) {
            if (!vertex.isVisited()) {
                return false;
            }
        }

        return true;
    }
}
