package resource;

import database.ClientsDB;
import database.ParcelsDB;
import model.Address;
import model.Client;
import model.Parcel;
import model.ParcelStatus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DataManager {
    private static final String CLIENTS_FILE_PATH = "Clients.csv";
    private static final String PACKAGES_FILE_PATH = "Packages.csv";
    private static final String DELIMITER = ";";

    private final ClientsDB clientsDB;
    private final ParcelsDB parcelsDB;

    HashMap<String, ArrayList<Parcel>> parcelsPerDay;

    public DataManager() {
        this.clientsDB = new ClientsDB();
        this.parcelsDB = new ParcelsDB();
        readData();
    }

    public void readData() {
        readClients();
        readParcels();
    }

    public void sortClientsByName() {
        this.clientsDB.sortClientsByName();
    }

    public ArrayList<Client> getClients() {
        return this.clientsDB.getClientsList();
    }

    public ParcelStatus getParcelStatusByIDSequentially(ArrayList<Parcel> parcels, Long id) {
        return Searcher.getParcelStatusByIDSequentially(parcels, id);
    }

    public ParcelStatus getParcelStatusByIDBinary(ArrayList<Parcel> parcels, Long id) {
        return Searcher.getParcelStatusByIDBinary(parcels, id);
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
                        this.clientsDB.getClientByID(Long.parseLong(lineValues[6]))
                );

                parcelsDB.addParcel(parcel);
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

                this.clientsDB.addClient(client);
            }
        } catch (FileNotFoundException e) {
            throw new Error("No such file: " + CLIENTS_FILE_PATH);
        } catch (IOException e) {
            throw new Error("I/O error: " + e);
        }
    }
}
