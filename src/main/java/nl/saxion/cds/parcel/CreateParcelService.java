package nl.saxion.cds.parcel;

import nl.saxion.cds.client.ClientDAO;

public class CreateParcelService {
    private ClientDAO clientDAO;
    private ParcelDAO parcelDAO;

    public CreateParcelService(ClientDAO clientDAO, ParcelDAO parcelDAO) {
        this.clientDAO = clientDAO;
        this.parcelDAO = parcelDAO;
    }

    public void createParcel(Long id, int length, int breadth, int height, double weight, String entryDate, Long clientID) {
        var parcel = new Parcel(id, length, breadth, height, weight, entryDate, this.clientDAO.get(clientID));
        this.parcelDAO.save(parcel);
    }
}
