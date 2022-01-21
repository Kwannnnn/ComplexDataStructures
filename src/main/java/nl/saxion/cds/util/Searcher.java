package nl.saxion.cds.util;

import nl.saxion.cds.exception.ParcelNotFoundException;
import nl.saxion.cds.parcel.Parcel;
import nl.saxion.cds.parcel.ParcelStatus;

import java.util.*;

public class Searcher {
    private Searcher() {

    }

    public static <T extends Comparable<T>> PriorityQueue<T> findTopK(Collection<T> list, int k, Comparator<T> comparator) {
        var priorityQueue = new PriorityQueue<T>();
        for (var element : list) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(element);
            } else {
                assert priorityQueue.peek() != null;
                // smallest element of the priority queue < the current element of the list
                if (comparator.compare(priorityQueue.peek(), element) < 0) {
                    priorityQueue.poll();
                    priorityQueue.add(element);
                }
            }
        }

        return priorityQueue;
    }

    /**
     * Finds the status of a parcel by ID in a sequential manner.
     *
     * @param parcels the list of parcel to search in
     * @param date    the date to search for
     * @return the ParcelStatus if found, or null if not found.
     */
    public static List<Parcel> getAllParcelsForADay(Collection<Parcel> parcels, String date) {
        var result = new ArrayList<Parcel>();
        for (var parcel : parcels) {
            if (parcel.getEntryDate().equals(date))
                result.add(parcel);
        }

        return result;
    }

    public static HashMap<Long, List<Parcel>> getParcelsPerCustomer(Collection<Parcel> parcels) {
        var result = new HashMap<Long, List<Parcel>>();

        for (var parcel : parcels) {
            var customerID = parcel.getClient().getId();
            if (!result.containsKey(customerID)) {
                result.put(customerID, new ArrayList<>());
            }
            result.get(customerID).add(parcel);
        }

        return result;
    }

    /**
     * Finds the status of a parcel by ID in a sequential manner.
     *
     * @param parcels the list of parcel to search in
     * @param id      the ID of the Parcel to look for
     * @return the ParcelStatus if found, or null if not found.
     */
    public static ParcelStatus getParcelStatusByIDSequentially(List<Parcel> parcels, Long id) throws ParcelNotFoundException {
        for (var parcel : parcels) {
            if (parcel.getId().equals(id))
                return parcel.getParcelStatus();
        }

        throw new ParcelNotFoundException();
    }

    /**
     * Finds the status of a parcel by ID in a binary manner.
     *
     * @param parcels the list of parcels to search in
     * @param id      the ID of the Parcel to look for
     * @return the ParcelStatus if found, or null if not found.
     */
    public static ParcelStatus getParcelStatusByIDBinary(List<Parcel> parcels, Long id) throws ParcelNotFoundException {
        int begin = 0;
        int end = parcels.size();

        do {
            int middle = (begin + end) / 2;
            Long parcelID = parcels.get(middle).getId();
            if (parcelID < id) begin = middle + 1;
            else if (parcelID.equals(id)) return parcels.get(middle).getParcelStatus();
            else end = middle - 1;
        } while (begin < end);

        throw new ParcelNotFoundException();
    }
}
