package nl.saxion.cds.parcel;

import nl.saxion.cds.client.Client;
import java.util.Arrays;

public class Parcel implements Comparable<Parcel> {
    private final Long id;
    private int length;
    private int width;
    private int height;
    private double weight;
    private String entryDate;
    private ParcelStatus parcelStatus;
    private final Client client;


    public Parcel(Long id, int length, int width, int height, double weight, String entryDate,
                  Client client) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.entryDate = entryDate;
        this.parcelStatus = ParcelStatus.DC;
        this.client = client;

        // assign dimensions by sorting them in ascending order
        // width < length < height
        // since height is the least important and we want to minimize our area use when packaging
        int[] dimensions = new int[]{width, length, height};
        Arrays.sort(dimensions);
        this.width = dimensions[0];
        this.length = dimensions[1];
        this.height = dimensions[2];
    }

    /**
     * Compares two parcels by the smallest side measurement.
     * @param parcel the Parcel to be compared
     * @return a negative integer, zero, or a positive integer representing whether this instance is less than,
     * equal to, or greater than the given one.
     */
    @Override
    public int compareTo(Parcel parcel) {
        return Math.max(this.length, this.width) - Math.max(parcel.length, parcel.width);
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

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getMinArea() {
        //since the dimensions are already sorted by size
        return width * length;
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
        return "Parcel " + id + " (" + client.getId() + ") ";
    }
}
