package model;

public class Parcel implements Comparable<Parcel> {
    private final Long id;
    private int length;
    private int breadth;
    private int height;
    private double weight;
    private String entryDate;
    private ParcelStatus parcelStatus;
    private final Client client;

    public Parcel(Long id, int length, int breadth, int height, double weight, String entryDate,
                  Client client) {
        this.id = id;
        this.length = length;
        this.breadth = breadth;
        this.height = height;
        this.weight = weight;
        this.entryDate = entryDate;
        this.parcelStatus = ParcelStatus.DC;
        this.client = client;
    }

    /**
     * Compares two parcels by the distance from the distribution center of the client's address.
     * @param parcel a Parcel to compare to
     * @return -1 if the distance from DC is smaller than the distance for the other parcel
     * 0 if the distances of both parcels are equal
     * 1 if the distance from DC is larger than the distance of the other parcel
     */
    @Override
    public int compareTo(Parcel parcel) {
        return calculateDistanceFromDC(this.client.getAddress()) - calculateDistanceFromDC(parcel.client.getAddress());
    }

    /**
     * Calculates the Manhattan distance between an Address and the Distribution center (located at x: 375, y: 375)
     * @param address an Address
     * @return a positive integer the distance
     */
    private int calculateDistanceFromDC(Address address) {
        return Math.abs((address.x() - 375)) + Math.abs((address.y() - 375));
    }

    public int getLength() {
        return this.length;
    }

    public int getBreadth() {
        return this.breadth;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
//        return "Parcel " + id +
//                " (" + client.getAddress().x() + "," + client.getAddress().y() + "), " +
//                calculateDistanceFromDC(this.client.getAddress());
        return "Parcel " + id +
                " (" + client.getId() + ") " + calculateDistanceFromDC(this.client.getAddress());
    }
}
