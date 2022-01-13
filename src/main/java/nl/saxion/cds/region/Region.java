package nl.saxion.cds.region;

import nl.saxion.cds.client.Address;
import nl.saxion.cds.parcel.Parcel;

import java.util.ArrayList;
import java.util.List;

public class Region {
    private final Coordinate topLeft;
    private final Coordinate topRight;
    private final Coordinate bottomLeft;
    private final Coordinate bottomRight;

    private List<Parcel> dailyPackages;

    public Region(Coordinate topLeft, Coordinate topRight, Coordinate bottomLeft, Coordinate bottomRight) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
        this.dailyPackages = new ArrayList<>();
    }

    public void addParcel(Parcel parcel) {
        this.dailyPackages.add(parcel);
    }

    public boolean addressInRange(Address address) {
        return (address.x() >= this.topLeft.getX() && address.x() <= this.topRight.getX())
                && (address.y() >= this.topLeft.getY() && address.y() <= this.bottomRight.getY());
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
