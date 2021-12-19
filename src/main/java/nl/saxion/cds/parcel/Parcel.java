package nl.saxion.cds.parcel;

import nl.saxion.cds.client.Client;

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
        // use breadth and length for now
//        super("Parcel id: " + id, breadth, length);
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
     * Compares two parcels by the biggest side measurement.
     * @param parcel the Parcel to be compared
     * @return a negative integer, zero, or a positive integer representing whether this instance is less than,
     * equal to, or greater than the given one.
     */
    @Override
    public int compareTo(Parcel parcel) {
        return Math.max(this.length, this.breadth) - Math.max(parcel.length, parcel.breadth);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Parcel anotherParcel = (Parcel) obj;
        return this.id.equals(anotherParcel.id);
    }

    public Long getId() {
        return this.id;
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

    public int getMinArea() {
        return Math.min(Math.min(this.height * this.breadth, this.height * this.length), this.length * this.breadth);
    }

    public String getEntryDate() {
        return this.entryDate;
    }

    public Client getClient() {
        return this.client;
    }

    public ParcelStatus getParcelStatus() {
        return this.parcelStatus;
    }

    @Override
    public String toString() {
//        return "Parcel " + id +
//                " (" + client.getAddress().x() + "," + client.getAddress().y() + "), " +
//                calculateDistanceFromDC(this.client.getAddress());
        return "Parcel " + id +
                " (" + client.getId() + ") ";
    }
}
