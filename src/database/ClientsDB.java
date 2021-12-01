package database;

import model.Client;
import resource.Sorter;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientsDB {
    private final HashMap<Long, Client> clients;
    private final ArrayList<Client> clientsList;

    public ClientsDB() {
        this.clients = new HashMap<>();
        this.clientsList = new ArrayList<>();
    }

    public void addClient(Client client) {
        this.clients.put(client.getId(), client);
        this.clientsList.add(client);
    }

    public Client getClientByID(Long id) {
        return clients.get(id);
    }

    public void sortClientsByName() {
        Sorter.sort(clientsList);
    }

    public ArrayList<Client> getClientsList() {
        return this.clientsList;
    }
}
