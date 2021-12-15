package database;

import model.Client;
import model.Parcel;
import model.ParcelStatus;
import util.Searcher;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class DataManager {
    private static DataManager instance;
    private ClientsDB clientsDB;
    private ParcelsDB parcelsDB;


    private DataManager() {
        this.clientsDB = new ClientsDB();
        this.parcelsDB = new ParcelsDB();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }

        return instance;
    }

    public void sortClientsByName() {
        this.clientsDB.sortClientsByName();
    }

    public ArrayList<Client> getClients() {
        return this.clientsDB.getClientsList();
    }

    public PriorityQueue<Parcel> getRouteForADay(String date) {
        return this.parcelsDB.getAllRoutesPerDay().get(date);
    }

    public ParcelStatus getParcelStatusByIDSequentially(ArrayList<Parcel> parcels, Long id) {
        return Searcher.getParcelStatusByIDSequentially(parcels, id);
    }

    public ParcelStatus getParcelStatusByIDBinary(ArrayList<Parcel> parcels, Long id) {
        return Searcher.getParcelStatusByIDBinary(parcels, id);
    }

    public ClientsDB getClientsDB() {
        return clientsDB;
    }

    public ParcelsDB getParcelsDB() {
        return parcelsDB;
    }
}
