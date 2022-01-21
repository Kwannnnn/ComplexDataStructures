package nl.saxion.cds.io;

import nl.saxion.cds.exception.RecordNotLoadedException;
import nl.saxion.cds.parcel.CreateParcelService;

public class ParcelsCsvLoader extends CsvFileLoader {
    private final CreateParcelService service;

    public ParcelsCsvLoader(String filename, CreateParcelService service) {
        super(filename);
        this.service = service;
    }

    @Override
    protected void save(String[] args) throws RecordNotLoadedException {
        this.service.createParcel(
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
