package nl.saxion.cds.graph;

public class Edge {
    private Vertex src;
    private Vertex dest;
    private int weight;

    public Edge(Vertex src, Vertex dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
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
}
