package nl.saxion.cds.util;

import nl.saxion.cds.parcel.Parcel;

import nl.saxion.cds.comparator.AreaDescComparator;
import nl.saxion.cds.tree.PackingBST;
import nl.saxion.cds.util.Sorter;
import nl.saxion.cds.van.Van;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Packer {
    public static final int DEFAULT_VAN_LENGTH = 580;
    public static final int DEFAULT_VAN_WIDTH = 300;

    public static List<PackingBST> packFirstFitDecreasing(List<Parcel> parcels) {
        var result = new ArrayList<PackingBST>();

        var parcelsCopy = new ArrayList<>(parcels);

        while (parcelsCopy.size() > 0) {
            Van van = new Van(DEFAULT_VAN_LENGTH, DEFAULT_VAN_WIDTH);
            var tree = new PackingBST(van.getLength(), van.getWidth());

            // assuming that each parcel can fit into the van
            Sorter.sort(parcelsCopy, new AreaDescComparator());

            Iterator<Parcel> iterator = parcelsCopy.iterator();
            while (iterator.hasNext()) {
                if (tree.insert(iterator.next())) {
                    iterator.remove();
                }
            }

            result.add(tree);
        }

        return result;
    }
}
