package nl.saxion.cds;

import nl.saxion.cds.client.ClientDAO;
import nl.saxion.cds.parcel.ParcelDAO;

public class DataManager {
    private final ClientDAO clientDAO;
    private final ParcelDAO parcelDAO;

    public DataManager() {
        this.clientDAO = new ClientDAO();
        this.parcelDAO = new ParcelDAO();
    }

    public ClientDAO getClientDAO() {
        return this.clientDAO;
    }

    public ParcelDAO getParcelDAO() {
        return this.parcelDAO;
    }
}
