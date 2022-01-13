package nl.saxion.cds.comparator;

import nl.saxion.cds.client.Client;
import nl.saxion.cds.region.Coordinate;

import java.util.Comparator;

public class DistanceComparator implements Comparator<Client> {

    /**
     * Compares two clients by the distance from the distribution center of their addresses.
     * @param c1 the first Client to be compared
     * @param c2 the second Client to be compared
     * @return a negative integer, zero, or a positive integer indicating whether the first argument is less than, equal
     * to, or greater than the second.
     */
    @Override
    public int compare(Client c1, Client c2) {
        return calculateDistanceFromDC(c1.getAddress()) - calculateDistanceFromDC(c2.getAddress());
    }

    /**
     * Calculates the Manhattan distance between an Address and the Distribution center (located at x: 375, y: 375)
     * @param address an Address
     * @return a positive integer the distance
     */
    private int calculateDistanceFromDC(Coordinate address) {
        return (int) (Math.abs((address.getX() - 375)) + Math.abs((address.getY() - 375)));
    }
}
