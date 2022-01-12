package nl.saxion.cds.van;

import nl.saxion.cds.parcel.Parcel;

import java.util.Stack;

public class Van {
    private int length;
    private int width;
    private int availableArea;
    private Stack<Parcel> parcels;

    public Van(int length, int width) {
        this.length = length;
        this.width = width;
        this.availableArea = length * width;
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
                parcelBreadth <= this.width &&
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

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return this.length;
    }
}
