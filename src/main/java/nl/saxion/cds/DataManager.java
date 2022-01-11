package nl.saxion.cds;

import nl.saxion.cds.client.ClientDAO;
import nl.saxion.cds.io.ClientsCsvLoader;
import nl.saxion.cds.client.CreateClientService;
import nl.saxion.cds.parcel.CreateParcelService;
import nl.saxion.cds.parcel.ParcelDAO;
import nl.saxion.cds.io.ParcelsCsvLoader;

import java.io.IOException;

public class DataManager {
    private static final String PACKAGES_FILENAME = "Packages.csv";
    private static final String CLIENTS_FILENAME = "Clients.csv";

    private final ClientDAO clientDAO;
    private final ParcelDAO parcelDAO;

    public DataManager() throws IOException {
        this.clientDAO = new ClientDAO();
        this.parcelDAO = new ParcelDAO();
        readData();
    }

    private void readData() throws IOException {
        new ClientsCsvLoader(CLIENTS_FILENAME, new CreateClientService(this.clientDAO)).loadFile();
        new ParcelsCsvLoader(PACKAGES_FILENAME, new CreateParcelService(this.clientDAO, this.parcelDAO)).loadFile();
    }

    public ClientDAO getClientDAO() {
        return this.clientDAO;
    }

    public ParcelDAO getParcelDAO() {
        return this.parcelDAO;
    }
}
