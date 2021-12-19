package nl.saxion.cds;

import nl.saxion.cds.client.ClientService;
import nl.saxion.cds.client.CreateClientService;
import nl.saxion.cds.parcel.ParcelService;
import nl.saxion.cds.parcel.CreateParcelService;
import nl.saxion.cds.client.ClientsCsvLoader;
import nl.saxion.cds.parcel.ParcelsCsvLoader;

import java.io.IOException;

public class SystemFacade {
    private static final String PACKAGES_FILENAME = "Packages.csv";
    private static final String CLIENTS_FILENAME = "Clients.csv";

    private final DataManager dataManager;
    private final CreateParcelService createParcelService;
    private final CreateClientService createClientService;
    private final ParcelService parcelService;
    private final ClientService clientService;

    public SystemFacade(DataManager dataManager) throws IOException {
        this.dataManager = dataManager;
        this.clientService = new ClientService(this.dataManager.getClientDAO());
        this.parcelService = new ParcelService(this.dataManager.getParcelDAO());
        this.createClientService = new CreateClientService(this.dataManager.getClientDAO());
        this.createParcelService = new CreateParcelService(this.dataManager.getClientDAO(), this.dataManager.getParcelDAO());
        readData();
    }

    private void readData() throws IOException {
        new ClientsCsvLoader(CLIENTS_FILENAME, this.createClientService).loadFile();
        new ParcelsCsvLoader(PACKAGES_FILENAME, this.createParcelService).loadFile();
    }

    public String getParcelStatus(String id) {
        return this.parcelService.getParcelStatus(id);
    }
}
