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

    public Vertex getSrc() {
        return src;
    }

    public Vertex getDest() {
        return dest;
    }

    public int getWeight() {
        return weight;
    }
}
