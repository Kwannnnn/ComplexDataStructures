package util.io;

import database.DataManager;

public class ClientsLoader extends CsvFileLoader {
    private final DataManager dataManager;

    public ClientsLoader(String filename, DataManager dataManager) {
        super(filename);
        this.dataManager = dataManager;
    }

    @Override
    protected void addToManager(String[] args) {
        this.dataManager.addNewClient(
                Long.parseLong(args[0]),
                args[1],
                args[2],
                Integer.parseInt(args[3]),
                Integer.parseInt(args[4])
        );
    }
}
