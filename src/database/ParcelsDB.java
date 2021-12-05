package database;

import model.DistanceComparator;
import model.Parcel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class ParcelsDB {
    private static final String PACKAGES_FILE_PATH = "Packages.csv";

    private final HashMap<String, PriorityQueue<Parcel>> routesPerDay;
    private final ArrayList<Parcel> parcelsList;

    public ParcelsDB() {
        this.routesPerDay = new HashMap<>();
        this.parcelsList = new ArrayList<>();
        readParcels();
    }

    public void addParcel(Parcel parcel) {
        addParcelToRoute(parcel);
        this.parcelsList.add(parcel);
    }

    public HashMap<String, PriorityQueue<Parcel>> getAllRoutesPerDay() {
        return this.routesPerDay;
    }

    private void addParcelToRoute(Parcel parcel) {
        var date = parcel.getEntryDate();
        if (!this.routesPerDay.containsKey(date)) {
            this.routesPerDay.put(date, new PriorityQueue<>(new DistanceComparator()));
        }
        this.routesPerDay.get(date).add(parcel);
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
                lineValues = currentLine.split(";");

                var parcel = new Parcel(
                        Long.parseLong(lineValues[0]),
                        Integer.parseInt(lineValues[1]),
                        Integer.parseInt(lineValues[2]),
                        Integer.parseInt(lineValues[3]),
                        Double.parseDouble(lineValues[4]),
                        lineValues[5],
                        Database.getInstance().getClientsDB().getClientByID(Long.parseLong(lineValues[6]))
                );

                addParcel(parcel);
            }
        } catch (FileNotFoundException e) {
            throw new Error("No such file: " + PACKAGES_FILE_PATH);
        } catch (IOException e) {
            throw new Error("I/O error: " + e);
        }
    }
}
