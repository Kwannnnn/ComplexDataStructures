package database;

import model.Address;
import model.Client;
import util.Sorter;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class acts as a database that stores data about clients for the delivery service.
 */
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

    /**
     * Reads the Clients from the Clients.csv into an ArrayList and a HashMap, where the key of the HashMap is the
     * client's ID and the value a Client object. The use of these 2 different data structures is to facilitate different
     * usage and algorithm implementations.
     */
    private void readClients() {
        try {
            var in = getClass().getResourceAsStream("/" + CLIENTS_FILE_PATH);
            if (in == null) {
                throw new Error("Warning: Could not find " + CLIENTS_FILE_PATH + " file!");
            }
            var reader = new BufferedReader(new InputStreamReader(in));

            String currentLine = reader.readLine(); // Skip header line
            String[] lineValues;
            while ((currentLine = reader.readLine()) != null) {
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
