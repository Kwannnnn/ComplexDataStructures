package database;

public class Database {
    private static Database instance;
    private ClientsDB clientsDB;
    private ParcelsDB parcelsDB;

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public void loadData() {
        this.clientsDB = new ClientsDB();
        this.parcelsDB = new ParcelsDB();
    }

    public ClientsDB getClientsDB() {
        return this.clientsDB;
    }

    public ParcelsDB getParcelsDB() {
        return this.parcelsDB;
    }
}
