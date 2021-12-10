package database;

import model.Address;
import model.Client;
import util.Sorter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientsDB {
    private static final String CLIENTS_FILE_PATH = "Clients.csv";

    private final HashMap<Long, Client> clients;
    private final ArrayList<Client> clientsList;

    public ClientsDB() {
        this.clients = new HashMap<>();
        this.clientsList = new ArrayList<>();
        readClients();
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

    private void readClients() {
        try {
            var bufferedReader = new BufferedReader(new FileReader(CLIENTS_FILE_PATH));

            String currentLine = bufferedReader.readLine(); // Skip header line
            String[] lineValues;
            while ((currentLine = bufferedReader.readLine()) != null) {
                lineValues = currentLine.split(";");

                var clientAddress = new Address(
                        Integer.parseInt(lineValues[3]),
                        Integer.parseInt(lineValues[4])
                );

                var client = new Client(
                        Long.parseLong(lineValues[0]),
                        lineValues[1],
                        lineValues[2],
                        clientAddress
                );

                addClient(client);
            }
        } catch (FileNotFoundException e) {
            throw new Error("No such file: " + CLIENTS_FILE_PATH);
        } catch (IOException e) {
            throw new Error("I/O error: " + e);
        }
    }
}
