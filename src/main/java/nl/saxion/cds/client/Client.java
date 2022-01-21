package nl.saxion.cds.client;

import nl.saxion.cds.region.Coordinate;

public class Client implements Comparable<Client> {
    private final Long id;
    private final String name;
    private final Coordinate address;

    public Client(Long id, String name, String initials, Coordinate address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Coordinate getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        return name;
    }

    /**
     * Compares two clients by their names alphabetically.
     * @param c the client to be compared to
     * @return a negative integer, zero, or a positive integer representing whether this instance is less than,
     * equal to, or greater than the given one.
     */
    @Override
    public int compareTo(Client c) {
        return this.name.compareTo(c.name);
    }
}
