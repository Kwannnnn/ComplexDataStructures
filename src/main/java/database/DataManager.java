package database;

import model.Address;
import model.Client;
import model.Parcel;
import model.ParcelStatus;
import util.Searcher;
import util.io.ClientsLoader;
import util.io.ParcelsLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class DataManager {
    private static final String PACKAGES_FILENAME = "Packages.csv";
    private static final String CLIENTS_FILENAME = "Clients.csv";

    private static DataManager instance;

    private final ClientsDB clientsDB;
    private final ParcelsDB parcelsDB;

    private DataManager() {
        this.clientsDB = new ClientsDB();
        this.parcelsDB = new ParcelsDB();
    }

    public static DataManager getInstance() {
        return instance == null ? new DataManager() : instance;
    }

    public void init() throws IOException {
        new ParcelsLoader(PACKAGES_FILENAME, this).loadFile();
        new ClientsLoader(CLIENTS_FILENAME, this).loadFile();
    }

    public void addNewClient(Long id, String name, String initials, int addressX, int addressY) {
        this.clientsDB.addClient(new Client(
                id, name, initials, new Address(addressX, addressY)
        ));
    }

    public void addNewParcel(Long id, int length, int breadth, int height, double weight, String entryDate,
                             Long clientID) {
        this.parcelsDB.addParcel(new Parcel(
                id, length, breadth, height, weight, entryDate, getClientByID(clientID)
        ));
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

    private Client getClientByID(Long id) {
        return this.clientsDB.getClientByID(id);
    }
}
