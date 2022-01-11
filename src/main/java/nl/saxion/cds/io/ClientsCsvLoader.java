package nl.saxion.cds.io;

import nl.saxion.cds.client.CreateClientService;
import nl.saxion.cds.io.CsvFileLoader;

public class ClientsCsvLoader extends CsvFileLoader {
    private final CreateClientService service;

    public ClientsCsvLoader(String filename, CreateClientService service) {
        super(filename);
        this.service = service;
    }

    @Override
    protected void save(String[] args) {
        this.service.createClient(
                Long.parseLong(args[0]),
                args[1],
                args[2],
                Integer.parseInt(args[3]),
                Integer.parseInt(args[4])
        );
    }
}
