package nl.saxion.cds.graph;

import nl.saxion.cds.region.Coordinate;

public class Vertex {
    private String label;
    private Coordinate address;
    private boolean isVisited;

    public Vertex(String label, Coordinate address) {
        this.label = label;
        this.address = address;
        this.isVisited = false;
    }

    public String getLabel() {
        return this.label;
    }

    public Coordinate getAddress() {
        return this.address;
    }

    public boolean isVisited() {
        return this.isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Vertex vertex) {
            return this.label.equals(vertex.label) && this.address.equals(vertex.address);
        }
        return false;
    }
}
