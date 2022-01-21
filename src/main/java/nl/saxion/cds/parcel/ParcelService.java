package nl.saxion.cds.parcel;

import nl.saxion.cds.exception.ParcelNotFoundException;
import nl.saxion.cds.util.Searcher;

import java.util.*;

public class ParcelService {
    private final ParcelDAO parcelDAO;

    public ParcelService(ParcelDAO parcelDAO) {
        this.parcelDAO = parcelDAO;
    }

    public Collection<Parcel> getAllParcels() {
        return this.parcelDAO.getAll();
    }

    public String getParcelStatus(String id) throws ParcelNotFoundException {
        var queryResult = parcelDAO.get(Long.parseLong(id));
        if (queryResult.isEmpty()) throw new ParcelNotFoundException();

        var parcel = queryResult.get();
        return switch (parcel.getParcelStatus()) {
            case DC -> "DC";
            case EN_ROUTE -> "En route";
            case DELIVERED -> "Delivered";
        };
    }

    public HashMap<Long, List<Parcel>> getParcelsPerCustomer(Collection<Parcel> parcels) {
        return Searcher.getParcelsPerCustomer(parcels);
    }

    public Collection<Parcel> getParcelsForADate(String date) {
        return Searcher.getAllParcelsForADay(this.parcelDAO.getAll(), date);
    }
}
