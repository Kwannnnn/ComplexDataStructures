package util.packing;

import model.Parcel;
import model.Van;
import model.comparators.AreaDescComparator;
import util.Sorter;

import java.util.List;

public class Packer {

    public static void packFirstFitDecreasing(List<Parcel> parcels, List<Van> vans) {
        // only 1 van for now
        var tree = new PackingBST(vans.get(0).getBreadth(), vans.get(0).getHeight());

        // assuming that each parcel can fit into the van
        Sorter.sort(parcels, new AreaDescComparator());

        for (var parcel : parcels) {
            tree.insert(parcel);

        }
    }

//    public void printTree(Node root) {
//        if (root == null) return;
//        System.out.println(root.);
//        printTree(root.left);
//        printTree(root.right);
//    }

}
