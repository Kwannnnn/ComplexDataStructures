import resource.ClientParser;
import model.Client;
import nl.saxion.app.SaxionApp;
import resource.PackageParser;

import java.util.Map;

public class StartApplication implements Runnable {

    public static final String CLIENTS_FILE_PATH = "Clients.csv";
    public static final String PACKAGES_FILE_PATH = "Package.csv";
    public static final String DELIMITER = ";";

    public static void main(String[] args) {
        SaxionApp.start(new StartApplication());
    }

    @Override
    public void run() {
        var clients = new ClientParser(CLIENTS_FILE_PATH, DELIMITER).getAllClients();
        var packages = new PackageParser(PACKAGES_FILE_PATH, DELIMITER).getAllPackages();
        for (Map.Entry<Long, Client> entry : clients.entrySet()) {
            SaxionApp.printLine(entry.getValue().toString());
        }
    }
}
