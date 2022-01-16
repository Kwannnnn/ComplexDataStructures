package nl.saxion.cds.graph;

public class Edge implements Comparable<Edge> {
    private Vertex src;
    private Vertex dest;
    private int weight;
    private boolean isIncluded;

    public Edge(Vertex src, Vertex dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.isIncluded = false;
    }

    public int getWeight() {
        return weight;
    }

    public Vertex getSource() {
        return src;
    }

    public Vertex getDestination() {
        return dest;
    }

    @Override
    public int compareTo(Edge anotherEdge) {
        return this.weight - anotherEdge.weight;
    }
}
