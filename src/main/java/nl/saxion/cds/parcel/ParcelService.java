package nl.saxion.cds.parcel;

import nl.saxion.cds.exception.ParcelNotFoundException;
import nl.saxion.cds.graph.UndirectedWeightedGraph;
import nl.saxion.cds.graph.Vertex;
import nl.saxion.cds.region.Coordinate;
import nl.saxion.cds.region.Region;
import nl.saxion.cds.region.RegionMap;
import nl.saxion.cds.tree.Node;
import nl.saxion.cds.util.Packer;
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
//
//    public List<List<Node<Parcel>>> getPackages(String date) {
//        var result = new ArrayList<List<Node<Parcel>>>();
//        System.out.println(this.parcelDAO.getParcelsForADay(date).size());
//        var filledVans = Packer.packFirstFitDecreasing(this.parcelDAO.getParcelsForADay(date));
//        for (var van : filledVans) {
//            result.add(van.toList());
//        }
//
//        return result;
//    }
//
//    public ArrayList<String> getAllParcelIDs() {
//        var result = new ArrayList<String>();
//        this.parcelDAO.getAll().forEach(parcel -> result.add(parcel.getId().toString()));
//        return result;
//    }
//
//    public UndirectedWeightedGraph getDailyPackagesPerRegion(String date) {
//        var r1Packages = this.parcelDAO.getDailyPackagesPerRegion(date).get(0);
//
//        var graph = new UndirectedWeightedGraph();
//        var vDC = new Vertex("DC", new Coordinate(375,375));
//        graph.addVertex(vDC);
//
//        for (var parcel : r1Packages) {
//            var v = new Vertex(parcel.getId().toString(), parcel.getClient().getAddress());
//            graph.addVertex(v);
//            for (var vertex : graph.getVertices()) {
//                if (!v.equals(vertex)) {
//                    graph.addEdge(vertex, v);
//                }
//            }
//        }
//
//        return graph;
//    }
//
//    public List<Region> getRegionsAsList() {
//        return List.of(this.parcelDAO.getRegionMap().getRegions());
//    }
//
//    public List<Parcel> getParcelsForADay(String date) {
//        var a = Searcher.getAllParcelsForADay(this.getAll(), date);
//        System.out.println(a);
//        return a;
//    }
//
//    public List<List<Parcel>> getDailyPackagesPerRegion(String date) {
//        var result = new ArrayList<List<Parcel>>();
//        this.regionMap.distributeParcels(getParcelsForADay(date));
//
//        for (var region : this.regionMap.getRegions()) {
//            result.add(region.getDailyPackages());
//        }
//
//        return result;
//    }
//
//
//    private void addParcelToCustomer(Parcel parcel) {
//        var customerID = parcel.getClient().getId();
//        if (!this.parcelsPerCustomer.containsKey(customerID)) {
//            this.parcelsPerCustomer.put(customerID, new ArrayList<>());
//        }
//        this.parcelsPerCustomer.get(customerID).add(parcel);
//    }
//
//    private void addParcelToADate(Parcel parcel) {
//        var date = parcel.getEntryDate();
//        if (!this.parcelsPerDay.containsKey(date)) {
//            this.parcelsPerDay.put(date, new ArrayList<>());
//        }
//        this.parcelsPerDay.get(date).add(parcel);
//    }
//
//    public RegionMap getRegionMap() {
//        return this.regionMap;
//    }
}
