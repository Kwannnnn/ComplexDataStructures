package nl.saxion.cds.graph.algorithms;

import nl.saxion.cds.graph.Edge;
import nl.saxion.cds.graph.Vertex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class KruskalAlgo {

    // A class to represent a subset for
    // union-find
    static class Subset {
        String parentVertexId;
        int rank;
    }

    HashMap<String, Subset> subsets = new HashMap<>();

    private final int nrOfVertices;
    private final Edge[] arrayOfEdges; // collection of all edges

    // Creates a graph with V vertices and E edges
    public KruskalAlgo(List<Vertex> vertices, List<nl.saxion.cds.graph.Edge> edges) {
        for (Edge edge : edges) {
            System.out.println(edge.getSource().getLabel() + "-->" + edge.getDestination().getLabel() + " " + edge.getWeight());
        }

        this.nrOfVertices = vertices.size();

        arrayOfEdges = edges.toArray(new Edge[0]);
        for (Vertex v : vertices) {
            Subset s = new Subset();
            s.parentVertexId = v.getLabel();
            s.rank = 0;
            subsets.put(v.getLabel(), s);
        }
    }

    // A utility function to find set of an
    // element i (uses path compression technique)
    String find(String vertexId) {
        // find root and make root as parent of i
        // (path compression)
        var foundVertex = subsets.get(vertexId);
        if (!foundVertex.parentVertexId.equals(vertexId)) {
            foundVertex.parentVertexId = find(foundVertex.parentVertexId);
        }

        return foundVertex.parentVertexId;
    }

    // A function that does union of two sets
    // of x and y (uses union by rank)
    void Union(String idVertex1, String idVertex2) {
        String v1ParentId = find(idVertex1);
        String v2ParentId = find(idVertex2);

        var vertex1Subset = subsets.get(v1ParentId);
        var vertex2Subset = subsets.get(v2ParentId);

        // Attach smaller rank tree under root
        // of high rank tree (Union by Rank)
        if (vertex1Subset.rank < vertex2Subset.rank) {
            vertex1Subset.parentVertexId = v2ParentId;
        } else if (vertex1Subset.rank > vertex2Subset.rank) {
            vertex2Subset.parentVertexId = v1ParentId;
        } else {
            // If ranks are equal, then make one as
            // root and increment its rank by one
            vertex2Subset.parentVertexId = v1ParentId;
            vertex1Subset.rank++;
        }
    }

    // The main function to construct MST using Kruskal's
    // algorithm
    public List<Edge> execute() {
        // This will store the resultant MST
        Edge[] result = new Edge[nrOfVertices];

        // An index variable, used for result[]
        int e = 0;

        // An index variable, used for sorted edges
        int i;

        // Step 1:  Sort all the edges in non-decreasing
        // order of their weight.
        Arrays.sort(arrayOfEdges);
        for (var edge : arrayOfEdges) {
            System.out.println(edge.getSource().getLabel() + "-->" + edge.getDestination().getLabel() + " " + edge.getWeight());
        }

        i = 0; // Index used to pick next edge

        // Number of edges to be taken is equal to V-1
        while (e < nrOfVertices - 1) {
            // Step 2: Pick the smallest edge. And increment
            // the index for next iteration
            Edge next_edge = arrayOfEdges[i++];

            String x = find(next_edge.getSource().getLabel());
            String y = find(next_edge.getDestination().getLabel());

            // If including this edge doesn't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (!x.equals(y)) {
                result[e++] = next_edge;
                Union(x, y);
            }
            // Else discard the next_edge
        }

        return Arrays.stream(result).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
