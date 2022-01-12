package nl.saxion.cds.parcel;

import nl.saxion.cds.util.tree.Node;
import nl.saxion.cds.util.packing.Packer;
import nl.saxion.cds.van.Van;

import java.util.ArrayList;
import java.util.List;

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

    public List<List<Node<Parcel>>> getPackages(String date) {
        var result = new ArrayList<List<Node<Parcel>>>();
        var filledVans = Packer.packFirstFitDecreasing(this.parcelDAO.getParcelsForADay(date));
        for (var van : filledVans) {
            result.add(van.toList());
        }

        return result;
    }

    public ArrayList<String> getAllParcelIDs() {
        var result = new ArrayList<String>();
        this.parcelDAO.getAll().forEach(parcel -> result.add(parcel.getId().toString()));
        return result;
    }
}
