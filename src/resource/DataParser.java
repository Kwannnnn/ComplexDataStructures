package resource;

import model.Address;
import model.Client;
import model.Parcel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class DataParser {
    private static final String CLIENTS_FILE_PATH = "Clients.csv";
    private static final String PACKAGES_FILE_PATH = "Packages.csv";
    private static final String DELIMITER = ";";

    private HashMap<Long, Client> clients;
    private PriorityQueue<Parcel> parcels;

    public DataParser() {
        this.clients = new HashMap<>();
        this.parcels = new PriorityQueue<>();
    }

    public void readData() {
        readClients();
        readParcels();
    }

    public HashMap<Long, Client> getClients() {
        return this.clients;
    }

    public PriorityQueue<Parcel> getParcels() {
        return this.parcels;
    }

    /**
     * Reads the Parcels from the Packages.csv into a PriorityQueue, where the priority of parcels is determined by the
     * distance from the distribution center. (the further from the DC, the further back in the queue)
     */
    private void readParcels() {
        try {
            var bufferedReader = new BufferedReader(new FileReader(PACKAGES_FILE_PATH));

            String currentLine = bufferedReader.readLine(); // Skip header line
            String[] lineValues;
            while ((currentLine = bufferedReader.readLine()) != null) {
                lineValues = currentLine.split(DELIMITER);

                var parcel = new Parcel(
                        Long.parseLong(lineValues[0]),
                        Integer.parseInt(lineValues[1]),
                        Integer.parseInt(lineValues[2]),
                        Integer.parseInt(lineValues[3]),
                        Double.parseDouble(lineValues[4]),
                        lineValues[5],
                        this.clients.get(Long.parseLong(lineValues[6]))
                );

                parcels.add(parcel);
            }
        } catch (FileNotFoundException e) {
            throw new Error("No such file: " + PACKAGES_FILE_PATH);
        } catch (IOException e) {
            throw new Error("I/O error: " + e);
        }
    }

    private void readClients() {
        try {
            var bufferedReader = new BufferedReader(new FileReader(CLIENTS_FILE_PATH));

            String currentLine = bufferedReader.readLine(); // Skip header line
            String[] lineValues;
            while ((currentLine = bufferedReader.readLine()) != null) {
                lineValues = currentLine.split(DELIMITER);

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

                this.clients.put(Long.parseLong(lineValues[0]), client);
            }
        } catch (FileNotFoundException e) {
            throw new Error("No such file: " + CLIENTS_FILE_PATH);
        } catch (IOException e) {
            throw new Error("I/O error: " + e);
        }
    }
}
