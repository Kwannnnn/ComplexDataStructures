package nl.saxion.cds.util;

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
     * @param parcels the list of parcel to search in
     * @param id the ID of the Parcel to look for
     * @return the ParcelStatus if found, or null if not found.
     */
    public static ParcelStatus getParcelStatusByIDSequentially(List<Parcel> parcels, Long id) {
        for (var parcel : parcels) {
            if (parcel.getId().equals(id))
                return parcel.getParcelStatus();
        }

        return null;
    }

    /**
     * Finds the status of a parcel by ID in a binary manner.
     * @param parcels the list of parcels to search in
     * @param id the ID of the Parcel to look for
     * @return the ParcelStatus if found, or null if not found.
     */
    public static ParcelStatus getParcelStatusByIDBinary(List<Parcel> parcels, Long id) {
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
