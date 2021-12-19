package nl.saxion.cds.comparator;

import nl.saxion.cds.parcel.Parcel;

import java.util.Comparator;

public class AreaDescComparator implements Comparator<Parcel> {

    @Override
    public int compare(Parcel p1, Parcel p2) {
        return p2.getMinArea() - p1.getMinArea();
    }
}
