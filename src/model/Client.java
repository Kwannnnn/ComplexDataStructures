package model;

public record Client(Long id, String name, String initials, Address address) {

    @Override
    public String toString() {
        return initials + " " + name + " " + address;
    }
}
