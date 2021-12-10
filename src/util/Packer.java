package util;

import model.comparators.AreaDescComparator;
import model.Parcel;
import model.Van;
import util.tree.BinarySearchTree;

import java.util.List;

public class Packer {
    private static BinarySearchTree<Parcel> possibilities;

    public static void packFirstFitDecreasing(List<Parcel> parcels, List<Van> vans) {
        possibilities = new BinarySearchTree<>(new AreaDescComparator());
        Sorter.sort(parcels, new AreaDescComparator());


    }
}
