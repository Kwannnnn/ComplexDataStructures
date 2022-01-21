package nl.saxion.cds;

import nl.saxion.cds.client.ClientDAO;
import nl.saxion.cds.exception.RecordNotLoadedException;
import nl.saxion.cds.io.ClientsCsvLoader;
import nl.saxion.cds.client.CreateClientService;
import nl.saxion.cds.parcel.CreateParcelService;
import nl.saxion.cds.parcel.ParcelDAO;
import nl.saxion.cds.io.ParcelsCsvLoader;
import nl.saxion.cds.region.RegionMap;

import java.io.IOException;

/**
 * A class that simulates a Database, loading and storing all data related
 * to the application.
 */
public class DataManager {
    private static final String PACKAGES_FILENAME = "Packages.csv";
    private static final String CLIENTS_FILENAME = "Clients.csv";

    private final RegionMap regionMap;
    private final ClientDAO clientDAO;
    private final ParcelDAO parcelDAO;

    public DataManager() throws IOException, RecordNotLoadedException {
        this.regionMap = new RegionMap(0,0,800, 200, 7);
        this.clientDAO = new ClientDAO();
        this.parcelDAO = new ParcelDAO();
        readData();
    }

    private void readData() throws IOException, RecordNotLoadedException {
        new ClientsCsvLoader(CLIENTS_FILENAME, new CreateClientService(this.clientDAO)).loadFile();
        new ParcelsCsvLoader(PACKAGES_FILENAME, new CreateParcelService(this.clientDAO, this.parcelDAO)).loadFile();
    }

    public ClientDAO getClientDAO() {
        return this.clientDAO;
    }

    public ParcelDAO getParcelDAO() {
        return this.parcelDAO;
    }

    public RegionMap getRegionMap() {
        return this.regionMap;
    }
}
