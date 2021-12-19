package nl.saxion.cds.client;

public class Client implements Comparable<Client> {
    private final Long id;
    private String name;
    private String initials;
    private Address address;

    public Client(Long id, String name, String initials, Address address) {
        this.id = id;
        this.name = name;
        this.initials = initials;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
//        return name + " " + address;
//        return "Client " + id;
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
