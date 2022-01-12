package nl.saxion.cds.util.packing;

import nl.saxion.cds.parcel.Parcel;
import nl.saxion.cds.van.Van;
import nl.saxion.cds.comparator.AreaDescComparator;
import nl.saxion.cds.util.Sorter;

import java.util.List;

public class Packer {

    public static PackingBST packFirstFitDecreasing(List<Parcel> parcels, List<Van> vans) {
        // only 1 van for now
        var tree = new PackingBST(vans.get(0).getBreadth(), vans.get(0).getHeight());

        // assuming that each parcel can fit into the van
        Sorter.sort(parcels, new AreaDescComparator());

        for (var parcel : parcels) {
            tree.insert(parcel);
        }

        return tree;
    }
}
