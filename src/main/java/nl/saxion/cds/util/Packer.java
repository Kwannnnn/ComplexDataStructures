package nl.saxion.cds.util;

import nl.saxion.cds.parcel.Parcel;

import nl.saxion.cds.comparator.AreaDescComparator;
import nl.saxion.cds.tree.PackingBST;
import nl.saxion.cds.util.Sorter;
import nl.saxion.cds.van.Van;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Packer {
    public static final int DEFAULT_VAN_LENGTH = 200;
    public static final int DEFAULT_VAN_WIDTH = 80;

    public static List<Van> packFirstFitDecreasing(Collection<Parcel> parcels) {
        var result = new ArrayList<Van>();

        var parcelsCopy = new ArrayList<>(parcels);

        while (parcelsCopy.size() > 0) {
            Van van = new Van(DEFAULT_VAN_LENGTH, DEFAULT_VAN_WIDTH);
            var placementBinarySearchTree = van.getPlacement();

            // assuming that each parcel can fit into the van
            Sorter.sort(parcelsCopy, new AreaDescComparator());

            Iterator<Parcel> iterator = parcelsCopy.iterator();
            while (iterator.hasNext()) {
                if (placementBinarySearchTree.insert(iterator.next())) {
                    iterator.remove();
                }
            }

            result.add(van);
        }

        return result;
    }
}