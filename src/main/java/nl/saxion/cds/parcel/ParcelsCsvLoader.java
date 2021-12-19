package nl.saxion.cds.parcel;

import nl.saxion.cds.util.io.CsvFileLoader;

public class ParcelsCsvLoader extends CsvFileLoader {
    private final CreateParcelService service;

    public ParcelsCsvLoader(String filename, CreateParcelService service) {
        super(filename);
        this.service = service;
    }

    @Override
    protected void save(String[] args) {
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
