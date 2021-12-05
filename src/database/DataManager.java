package database;

import model.Client;
import model.Parcel;
import model.ParcelStatus;
import resource.Searcher;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class DataManager {
    private final Database db;

    public DataManager() {
        this.db = Database.getInstance();
        this.db.loadData();
    }

    public void sortClientsByName() {
        this.db.getClientsDB().sortClientsByName();
    }

    public ArrayList<Client> getClients() {
        return this.db.getClientsDB().getClientsList();
    }

    public PriorityQueue<Parcel> getRouteForADay(String date) {
        return this.db.getParcelsDB().getAllRoutesPerDay().get(date);
    }

    public ParcelStatus getParcelStatusByIDSequentially(ArrayList<Parcel> parcels, Long id) {
        return Searcher.getParcelStatusByIDSequentially(parcels, id);
    }

    public ParcelStatus getParcelStatusByIDBinary(ArrayList<Parcel> parcels, Long id) {
        return Searcher.getParcelStatusByIDBinary(parcels, id);
    }
}
