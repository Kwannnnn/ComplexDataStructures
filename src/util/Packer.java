package util;

import model.comparators.AreaDescComparator;
import model.Parcel;
import model.Van;
import model.comparators.PackingComparator;
import util.tree.BinarySearchTree;

import java.util.List;

public class Packer {
    private static BinarySearchTree<Parcel> possibilities;

    public static void packFirstFitDecreasing(List<Parcel> parcels, List<Van> vans) {
        possibilities = new BinarySearchTree<>(new PackingComparator(vans.get(0).getBreadth(), vans.get(0).getHeight()));

        // assuming that each parcel can fit into the van
        Sorter.sort(parcels, new AreaDescComparator());


    }
}
