package nl.saxion.cds.graph;

import nl.saxion.cds.region.Coordinate;

public class Vertex {
    private String label;
    private Coordinate address;

    public Vertex(String label, Coordinate address) {
        this.label = label;
        this.address = address;
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    public String getLabel() {
        return label;
    }

    public Coordinate getAddress() {
        return address;
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
