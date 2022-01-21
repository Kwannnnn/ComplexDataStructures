package nl.saxion.cds.region;

import nl.saxion.cds.parcel.Parcel;

import java.util.ArrayList;
import java.util.List;

public class Region {
    private final Coordinate topLeft;
    private final Coordinate topRight;
    private final Coordinate bottomLeft;
    private final Coordinate bottomRight;

//    private List<Parcel> dailyPackages;

    public Region(Coordinate topLeft, Coordinate topRight, Coordinate bottomLeft, Coordinate bottomRight) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
//        this.dailyPackages = new ArrayList<>();
    }

//    public void addParcel(Parcel parcel) {
//        this.dailyPackages.add(parcel);
//    }

    public Coordinate getTopLeft() {
        return topLeft;
    }

    public Coordinate getTopRight() {
        return topRight;
    }

    public Coordinate getBottomLeft() {
        return bottomLeft;
    }

    public Coordinate getBottomRight() {
        return bottomRight;
    }

    public boolean addressInRange(Coordinate address) {
        return (address.getX() >= this.topLeft.getX() && address.getX() <= this.topRight.getX())
                && (address.getY() >= this.topLeft.getY() && address.getY() <= this.bottomRight.getY());
    }

    public List<Parcel> getDailyPackages() {
        return dailyPackages;
    }

    @Override
    public String toString() {
        return "Region" +
                "topLeft=" + topLeft +
                ", topRight=" + topRight +
                ", bottomLeft=" + bottomLeft +
                ", bottomRight=" + bottomRight;
    }
}
