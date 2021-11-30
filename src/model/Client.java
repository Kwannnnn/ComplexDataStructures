package model;

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

    @Override
    public int compareTo(Client o) {
        return this.name.compareTo(o.name);
    }
}
