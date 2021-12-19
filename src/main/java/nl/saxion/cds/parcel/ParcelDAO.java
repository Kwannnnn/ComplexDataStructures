package nl.saxion.cds.parcel;

import nl.saxion.cds.client.Client;
import nl.saxion.cds.comparator.DistanceComparator;
import nl.saxion.cds.db.DataObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 * This class acts as a data access layer that stores data about parcels for the delivery service.
 */
public class ParcelDAO implements DataObject<Parcel> {
    private final HashMap<Long, Parcel> parcels;
    private final HashMap<String, PriorityQueue<Client>> routesPerDay;

    public ParcelDAO() {
        this.parcels = new HashMap<>();
        this.routesPerDay = new HashMap<>();
    }

    @Override
    public Parcel get(Long id) {
        var parcel = Optional.ofNullable(this.parcels.get(id));
        return parcel.orElse(null);
    }

    @Override
    public Collection<Parcel> getAll() {
        return this.parcels.values();
    }

    @Override
    public void save(Parcel parcel) {
        this.parcels.put(parcel.getId(), parcel);
    }

    private void addParcelToRoute(Parcel parcel) {
        var date = parcel.getEntryDate();
        if (!this.routesPerDay.containsKey(date)) {
            this.routesPerDay.put(date, new PriorityQueue<>(new DistanceComparator()));
        }
        this.routesPerDay.get(date).add(parcel.getClient());
    }
}
