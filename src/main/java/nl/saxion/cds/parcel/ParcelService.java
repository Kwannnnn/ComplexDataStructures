package nl.saxion.cds.parcel;

import java.util.ArrayList;

public class ParcelService {
    private final ParcelDAO parcelDAO;

    public ParcelService(ParcelDAO parcelDAO) {
        this.parcelDAO = parcelDAO;
    }

    public String getParcelStatus(String id) {
        var parcel = parcelDAO.get(Long.parseLong(id));
        if (parcel == null) return "Parcel with " + id + " not found!";
        return switch (parcel.getParcelStatus()) {
            case DC -> "DC";
            case EN_ROUTE -> "En route";
            case DELIVERED -> "Delivered";
        };
    }

    public ArrayList<String> getAllParcelIDs() {
        var result = new ArrayList<String>();
        this.parcelDAO.getAll().forEach(parcel -> result.add(parcel.getId().toString()));
        return result;
    }
}
