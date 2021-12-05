package model;

import java.util.Comparator;

public class DistanceComparator implements Comparator<Parcel> {

    /**
     * Compares two parcels by the distance from the distribution center of the client's address.
     * @param p1 the first Parcel to be compared
     * @param p2 the second Parcel to be compared
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or
     * greater than the second.
     */
    @Override
    public int compare(Parcel p1, Parcel p2) {
        return calculateDistanceFromDC(p1.getClient().getAddress()) - calculateDistanceFromDC(p2.getClient().getAddress());
    }

    /**
     * Calculates the Manhattan distance between an Address and the Distribution center (located at x: 375, y: 375)
     * @param address an Address
     * @return a positive integer the distance
     */
    private int calculateDistanceFromDC(Address address) {
        return Math.abs((address.x() - 375)) + Math.abs((address.y() - 375));
    }
}
