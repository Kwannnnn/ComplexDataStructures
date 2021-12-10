package model;

import java.util.Stack;

public class Van {
    private final Long id;
    private int length;
    private int breadth;
    private int availableArea;
    private Stack<Parcel> parcels;

    public Van(Long id, int length, int breadth) {
        this.id = id;
        this.length = length;
        this.breadth = breadth;
        this.availableArea = length * breadth;
        this.parcels = new Stack<>();
    }

    public boolean parcelFits(Parcel parcel) {
        int parcelLength = parcel.getLength();
        int parcelBreadth = parcel.getBreadth();

        return parcelLength <= this.length &&
                parcelBreadth <= this.breadth &&
                parcelBreadth * parcelLength <= this.availableArea;
    }

    public void loadParcel(Parcel parcel) {
        if (parcelFits(parcel)) {
            this.parcels.push(parcel);
        }
        // Decrease available space
        this.availableArea -= parcel.getLength() * parcel.getBreadth();
    }

    public void deliverParcel() {
        var parcel = this.parcels.pop();
        this.availableArea += parcel.getLength() * parcel.getBreadth();
    }
}
