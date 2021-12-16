package util.io;

import database.DataManager;

public class ParcelsLoader extends CsvFileLoader {
    private final DataManager dataManager;

    public ParcelsLoader(String filename, DataManager dataManager) {
        super(filename);
        this.dataManager = dataManager;
    }

    @Override
    protected void addToManager(String[] args) {
        this.dataManager.addNewParcel(
                Long.parseLong(args[0]),
                Integer.parseInt(args[1]),
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3]),
                Double.parseDouble(args[4]),
                args[5],
                Long.parseLong(args[6])
        );
    }
}
