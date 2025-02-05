package nl.saxion.cds.client;

import nl.saxion.cds.db.DataObject;

import java.util.*;

public class ClientDAO implements DataObject<Client> {
    private final HashMap<Long, Client> clients;
    private final List<Client> clientsList;

    public ClientDAO() {
        this.clients = new HashMap<>();
        this.clientsList = new ArrayList<>();
    }

    @Override
    public Optional<Client> get(Long id) {
        return Optional.ofNullable(this.clients.get(id));
    }

    @Override
    public Collection<Client> getAll() {
        return this.clientsList;
    }

    @Override
    public void save(Client client) {
        this.clients.put(client.getId(), client);
        this.clientsList.add(client);
    }
}
