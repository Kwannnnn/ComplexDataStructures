package nl.saxion.cds.parcel;

import nl.saxion.cds.db.DataObject;

import java.util.*;

/**
 * This class acts as a data access layer that stores data about parcels for the delivery service.
 */
public class ParcelDAO implements DataObject<Parcel> {
    private final HashMap<Long, Parcel> parcels;

    public ParcelDAO() {
        this.parcels = new HashMap<>();
    }

    @Override
    public Optional<Parcel> get(Long id) {
        return Optional.ofNullable(this.parcels.get(id));
    }

    @Override
    public Collection<Parcel> getAll() {
        return this.parcels.values();
    }

    @Override
    public void save(Parcel parcel) {
        this.parcels.put(parcel.getId(), parcel);
    }
}
