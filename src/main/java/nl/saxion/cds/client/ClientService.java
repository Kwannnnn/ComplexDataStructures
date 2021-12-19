package nl.saxion.cds.client;

public class ClientService {
    private ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public Client getClient(Long id) {
        return this.clientDAO.get(id);
    }
}
