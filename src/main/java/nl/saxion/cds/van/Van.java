package nl.saxion.cds.van;

import nl.saxion.cds.parcel.Parcel;

import java.util.Stack;

public class Van {
    private final Long id;
    private int length;
    private int breadth;
    private int height;
    private int availableArea;
    private Stack<Parcel> parcels;

    public Van(Long id, int length, int breadth, int height) {
        this.id = id;
        this.length = length;
        this.height = height;
        this.breadth = breadth;
        this.availableArea = length * breadth;
        this.parcels = new Stack<>();
    }

    //TODO: problemo
    /**
     * Check whether a parcel can fit into the van.
     * @param parcel the parcel to check
     * @return a boolean indicating whether the given parcel can fit into the van
     */
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

    public int getBreadth() {
        return breadth;
    }

    public int getHeight() {
        return height;
    }
}
