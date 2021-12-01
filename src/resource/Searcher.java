package resource;

import model.Parcel;
import model.ParcelStatus;

import java.util.ArrayList;

public class Searcher {

    /**
     * Finds the status of a parcel by ID in a sequential manner.
     * @param parcels the list of parcel to search in
     * @param id the ID of the Parcel to look for
     * @return the ParcelStatus if found, or null if not found.
     */
    public static ParcelStatus getParcelStatusByIDSequentially(ArrayList<Parcel> parcels, Long id) {
        for (Parcel parcel : parcels) {
            if (parcel.getId().equals(id)) return parcel.getParcelStatus();
        }

        return null;
    }

    /**
     * Finds the status of a parcel by ID in a binary manner.
     * @param parcels the list of parcels to search in
     * @param id the ID of the Parcel to look for
     * @return the ParcelStatus if found, or null if not found.
     */
    public ParcelStatus getParcelStatusByIDBinary(ArrayList<Parcel> parcels, Long id) {
        int begin = 0;
        int end = parcels.size();

        do {
            int middle = (begin + end) / 2;
            Long parcelID = parcels.get(middle).getId();
            if (parcelID < id) begin = middle + 1;
            else if (parcelID.equals(id)) return parcels.get(middle).getParcelStatus();
            else end = middle - 1;
        }  while (begin < end);

        return null;
    }
}
