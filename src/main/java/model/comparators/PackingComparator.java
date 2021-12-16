package model.comparators;

import model.Parcel;

import java.awt.*;
import java.util.Comparator;

public class PackingComparator implements Comparator<Parcel> {
    private final int maxWidth;
    private final int maxHeight;


    public PackingComparator(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    // not completed
    @Override
    public int compare(Parcel p1, Parcel p2) {
//        boolean p2IsUsed = p2.getPackingStatus().isUsed();
//        Rectangle p2RightSpace = p2.getPackingStatus().getRightSpace();
//        Rectangle p2BelowSpace = p2.getPackingStatus().getBelowSpace();
        //prioritize packing from left to right and from top to bottom
//        if (p2IsUsed) {
            // if p1 can fit to the right of p2 and right of p2 is not used
//            if (p1.getBreadth() <= p2RightSpace.getWidth() && p1.getHeight() <= p2RightSpace.getHeight()) {
//                return 1;
            // if p1 can fit below p2
//            } else if (p1.getBreadth() <= p2BelowSpace.getWidth() && p1.getHeight() <= p2BelowSpace.getHeight()) {
//                return -1;
//            }
        // if p2 has not been used, meaning it is not yet loaded onto the van
//        } else {
//
//        }


        //
        return 0;
    }
}
