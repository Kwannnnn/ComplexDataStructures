package nl.saxion.cds;

import nl.saxion.cds.client.ClientService;
import nl.saxion.cds.parcel.ParcelService;

import java.io.IOException;
import java.util.List;

public class SystemFacade {
    private final DataManager data;
    private final ParcelService parcelService;
    private final ClientService clientService;

    public SystemFacade(DataManager dataManager) throws IOException {
        this.data = dataManager;
        this.clientService = new ClientService(dataManager.getClientDAO());
        this.parcelService = new ParcelService(dataManager.getParcelDAO());
    }

    public String getParcelStatus(String id) {
        return this.parcelService.getParcelStatus(id);
    }

    public List<String> getTop10Recipients() {
        return this.clientService.getTop10Recipients(this.data.getParcelDAO().getParcelsPerCustomer());
    }
}
