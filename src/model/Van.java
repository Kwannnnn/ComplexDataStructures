package model;

import java.util.Stack;

public class Van {
    private final Long id;
    private final int maxLength;
    private final int maxWidth;
    private final int maxHeight;
    private int availableArea;
    private Stack<Parcel> parcels;

    public Van(Long id, int maxLength, int maxWidth, int maxHeight, Stack<Parcel> parcels) {
        this.id = id;
        this.maxLength = maxLength;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.availableArea = maxLength * maxWidth;
        this.parcels = parcels;
    }

    public boolean parcelFits(Parcel parcel) {
        int parcelLength = parcel.getLength();
        int parcelBreadth = parcel.getBreadth();
        int parcelHeight = parcel.getHeight();

        return true;
    }

    public void loadParcel(Parcel parcel) {
        parcels.push(parcel);
        // Decrease available space
    }

    public void deliverParcel() {
        parcels.pop();
    }
}
