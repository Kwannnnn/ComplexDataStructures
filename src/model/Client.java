package model;

public class Client implements Comparable<Client> {
    private Long id;
    private String name;
    private String initials;
    private Address address;

    public Client(Long id, String name, String initials, Address address) {
        this.id = id;
        this.name = name;
        this.initials = initials;
        this.address = address;
    }

    // TODO: Maybe choose a better criteria for comparison of Clients
    /**
     * Compares two Client objects by name
     * @param client the Client to compare to
     * @return -1 if the name is alphabetically before the other client's name,
     * 0 if the names match,
     * 1 if the name is alphabetically after the other client's name
     */
    @Override
    public int compareTo(Client client) {
        return this.name.compareTo(client.name);
    }

    @Override
    public String toString() {
        return initials + " " + name + " " + address;
    }
}
