package nl.saxion.cds.parcel;

import nl.saxion.cds.db.DataObject;
import nl.saxion.cds.region.RegionMap;
import nl.saxion.cds.util.Searcher;

import java.util.*;

/**
 * This class acts as a data access layer that stores data about parcels for the delivery service.
 */
public class ParcelDAO implements DataObject<Parcel> {
    private final HashMap<Long, Parcel> parcels;
    private final HashMap<Long, List<Parcel>> parcelsPerCustomer;
    private final RegionMap regionMap;
    private final HashMap<String, List<Parcel>> parcelsPerDay;

    public ParcelDAO() {
        this.parcels = new HashMap<>();
        this.parcelsPerCustomer = new HashMap<>();
        // TODO: get top left and bottom right packages
        this.regionMap = new RegionMap(0,0,800, 200, 7);
        this.parcelsPerDay = new HashMap<>();
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
//        addParcelToADate(parcel.getEntryDate(), parcel);
        addParcelToCustomer(parcel);
    }

    public HashMap<Long, List<Parcel>> getParcelsPerCustomer() {
        return this.parcelsPerCustomer;
    }

    public List<Parcel> getParcelsForADay(String date) {
        var a = Searcher.getAllParcelsForADay(this.getAll(), date);
        System.out.println(a);
        return a;
    }

    public List<List<Parcel>> getDailyPackagesPerRegion(String date) {
        var result = new ArrayList<List<Parcel>>();
        this.regionMap.distributeParcels(getParcelsForADay(date));

        for (var region : this.regionMap.getRegions()) {
            result.add(region.getDailyPackages());
        }

        return result;
    }


    private void addParcelToCustomer(Parcel parcel) {
        var customerID = parcel.getClient().getId();
        if (!this.parcelsPerCustomer.containsKey(customerID)) {
            this.parcelsPerCustomer.put(customerID, new ArrayList<>());
        }
        this.parcelsPerCustomer.get(customerID).add(parcel);
    }

    private void addParcelToADate(Parcel parcel) {
        var date = parcel.getEntryDate();
        if (!this.parcelsPerDay.containsKey(date)) {
            this.parcelsPerDay.put(date, new ArrayList<>());
        }
        this.parcelsPerDay.get(date).add(parcel);
    }

    public RegionMap getRegionMap() {
        return this.regionMap;
    }
}
