//package nl.saxion.cds.graph;
//
//import nl.saxion.cds.region.Coordinate;
//
//import java.util.*;
//
//public class Dijkstra {
//    private int distance[];
//    // ids of settles vertices
//    private Set<Vertex> settled;
//    private PriorityQueue<Vertex> pQue;
//
//    // Total count of the vertices
//    private int totalVertices;
//
////    // TODO: change to map maybe
////    HashMap<String, List<Edge>> adjacent;
//
//    // Constructor of the class
//    public Dijkstra(int totalVertices) {
//        this.totalVertices = totalVertices;
//        distance = new int[totalVertices];
//        settled = new HashSet<>();
//        pQue = new PriorityQueue<>(totalVertices);
//    }
//
//    public void dijkstra(UndirectedWeightedGraph graph, int s) {
//        var adjacent = graph.getAdjVertices();
//
//        for (int j = 0; j < totalVertices; j++) {
//            // initializing the distance of every node to infinity (a large number)
//            distance[j] = Integer.MAX_VALUE;
//        }
//
//        // Adding the source node to pQue
//        pQue.add(graph.getVertices().iterator().next());
//
//        // distance of the source is always zero
//        distance[s] = 0;
//
//        while (settled.size() != totalVertices) {
//            // Terminating condition check when
//            // the priority queue contains zero elements, return
//            if (pQue.isEmpty()) {
//                return;
//            }
//
//            // Deleting the node that has the minimum distance from the priority queue
//            var vertex = pQue.remove();
//
//            // Adding the node whose distance is
//            // confirmed
//            if (settled.contains(vertex)) {
//                continue;
//            }
//
//            // We don't have to call eNeighbors(vertexLabel)
//            // if vertex is already present in the settled set.
//            settled.add(vertex);
//
//            List<Edge> edges = graph.getEdges(vertex);
//
//            eNeighbours(vertexLabel);
//        }
//    }
//
//    private void eNeighbours(Vertex vertex, List<Edge> edges) {
//        int edgeDist = -1;
//        int newDist = -1;
//
//        // All neighbors of vx
//        for (int j = 0; j < adjacent.get(ux).size(); j++) {
//            Vertex vx = adjacent.get(ux).get(j);
//
//            // If the current node hasn't been already processed
//            if (!settled.contains(vx.n)) {
//                edgeDist = vx.price;
//                newDist = distance[ux] + edgeDist;
//
//                // If the new distance is lesser in the cost
//                if (newDist < distance[vx.n]) {
//                    distance[vx.n] = newDist;
//                }
//
//                // Adding the current node to the priority queue pQue
//                pQue.add(new Vertex(vx.n, distance[vx.n]));
//            }
//        }
//    }
//}
