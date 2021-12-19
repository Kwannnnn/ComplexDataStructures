package nl.saxion.cds.client;

public class CreateClientService {
    private final ClientDAO clientDAO;

    public CreateClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public void createClient(Long id, String name, String initials, int addressX, int addressY) {
        this.clientDAO.save(new Client(id, name, initials, new Address(addressX, addressY)));
    }
}
