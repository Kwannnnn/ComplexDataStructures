package nl.saxion.cds.parcel;

import nl.saxion.cds.client.ClientDAO;
import nl.saxion.cds.exception.RecordNotLoadedException;

public class CreateParcelService {
    private ClientDAO clientDAO;
    private ParcelDAO parcelDAO;

    public CreateParcelService(ClientDAO clientDAO, ParcelDAO parcelDAO) {
        this.clientDAO = clientDAO;
        this.parcelDAO = parcelDAO;
    }

    public void createParcel(Long id, int length, int breadth, int height, double weight, String entryDate, Long clientID) throws RecordNotLoadedException {
        var queryResult = this.clientDAO.get(clientID);

        if (queryResult.isEmpty()) {
            throw new RecordNotLoadedException("Parcel could not be linked to a client with id "
                    + clientID + " because client does not exist");
        }

        var client = queryResult.get();
        var parcel = new Parcel(id, length, breadth, height, weight, entryDate, client);
        this.parcelDAO.save(parcel);
    }
}
