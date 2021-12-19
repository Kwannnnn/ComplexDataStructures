package nl.saxion.cds.client;

/**
 * This class represents a Client's address in x and y coordinates.
 */
public record Address(int x, int y) {

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
