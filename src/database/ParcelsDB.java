package database;

import model.Parcel;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ParcelsDB {
    private final PriorityQueue<Parcel> parcels;
    private final ArrayList<Parcel> parcelsList;

    public ParcelsDB() {
        this.parcels = new PriorityQueue<>();
        this.parcelsList = new ArrayList<>();
    }

    public void addParcel(Parcel parcel) {
        this.parcels.add(parcel);
        this.parcelsList.add(parcel);
    }
}
