package nl.saxion.cds;

import nl.saxion.cds.client.ClientService;
import nl.saxion.cds.exception.ParcelNotFoundException;
import nl.saxion.cds.graph.*;
import nl.saxion.cds.graph.algorithms.KruskalAlgo;
import nl.saxion.cds.graph.algorithms.PrimAlgo;
import nl.saxion.cds.parcel.Parcel;
import nl.saxion.cds.parcel.ParcelService;
import nl.saxion.cds.region.Coordinate;
import nl.saxion.cds.region.Region;
import nl.saxion.cds.region.RegionService;
import nl.saxion.cds.util.Packer;
import nl.saxion.cds.util.Searcher;
import nl.saxion.cds.van.Van;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class SystemFacade {
    private final RegionService regionService;
    private final ParcelService parcelService;
    private final ClientService clientService;

    public SystemFacade(DataManager dataManager) throws IOException {
        this.regionService = new RegionService(dataManager.getRegionMap());
        this.clientService = new ClientService(dataManager.getClientDAO());
        this.parcelService = new ParcelService(dataManager.getParcelDAO());
    }

    // Option 1 of the menu
    public String getParcelStatusUsingHashmap(String id) throws ParcelNotFoundException {
        return this.parcelService.getParcelStatus(id);
    }

    // Option 2 of the menu
    public String getParcelStatusSequentially(String id) throws ParcelNotFoundException {
        var allParcels = parcelService.getAllParcels();
        var parcelStatus = Searcher.getParcelStatusByIDSequentially(new ArrayList<>(allParcels), Long.valueOf(id));

        assert parcelStatus != null : "parcelStatus should not be null, otherwise an exception would have been thrown";

        return switch (parcelStatus) {
            case DC -> "DC";
            case EN_ROUTE -> "En route";
            case DELIVERED -> "Delivered";
        };
    }

    // Option 3 of the menu
    public String getParcelStatusBinary(String id) throws ParcelNotFoundException {
        var allParcels = parcelService.getAllParcels();
        var parcelStatus = Searcher.getParcelStatusByIDBinary(new ArrayList<>(allParcels), Long.valueOf(id));

        assert parcelStatus != null : "parcelStatus should not be null, otherwise an exception would have been thrown";

        return switch (parcelStatus) {
            case DC -> "DC";
            case EN_ROUTE -> "En route";
            case DELIVERED -> "Delivered";
        };
    }

    // Option 2 of the menu
    public List<String> getTop10Recipients() {
        var parcelsPerCustomer = getParcelsPerCustomer(this.parcelService.getAllParcels());
        return this.clientService.getTop10Recipients(parcelsPerCustomer);
    }

    //
    public List<Van> getLoadedVansForADayForARegion(String date, int regionIndex) {
        var parcelsForDate = this.parcelService.getParcelsForADate(date);
        var region = this.regionService.getAllRegions().get(regionIndex - 1);

        var parcelsForRegion = new ArrayList<Parcel>();
        for (var parcel : parcelsForDate) {
            if (region.addressInRange(parcel.getClient().getAddress())) {
                parcelsForRegion.add(parcel);
            }
        }

        return Packer.packFirstFitDecreasing(parcelsForRegion);
    }

    public List<Edge> getOptimalRouteUsingPrim(Collection<Parcel> parcels) {
        var graph = initializeGraph(parcels);

        PrimAlgo primAlgo = new PrimAlgo(graph.getVertices(), graph.getEdges());
        return primAlgo.execute();
    }

    public List<Edge> getOptimalRouteUsingKruskal(Collection<Parcel> parcels) {
        var graph = initializeGraph(parcels);

        KruskalAlgo kruskalAlgo = new KruskalAlgo(graph.getVertices(), graph.getEdges());
        return kruskalAlgo.execute();
    }


    private UndirectedWeightedGraph initializeGraph(Collection<Parcel> parcels) {
        var graph = new UndirectedWeightedGraph();

        var vDC = new Vertex("DC", new Coordinate(375,375));
        graph.addVertex(vDC);

        for (var parcel : parcels) {
            var v = new Vertex(parcel.getId().toString(), parcel.getClient().getAddress());
            graph.addVertex(v);
            for (var vertex : graph.getVertices()) {
                if (!v.equals(vertex)) {
                    graph.addEdge(vertex, v);
                }
            }
        }

        return graph;
    }

    public List<Region> getAllRegions() {
        return this.regionService.getAllRegions();
    }

    public int getRegionMapWidth() {
        return this.regionService.getRegionMapWidth();
    }

    public int getRegionMapLength() {
        return this.regionService.getRegionMapLength();
    }

    public Coordinate getRegionMapTopLeft() {
        return this.regionService.getRegionMapTopLeft();
    }

    public Coordinate getRegionMapTopRight() {
        return this.regionService.getRegionMapTopRight();
    }

    public Coordinate getRegionMapBottomLeft() {
        return this.regionService.getRegionMapBottomLeft();
    }

    public Coordinate getRegionMapBottomRight() {
        return this.regionService.getRegionMapBottomRight();
    }

//    public List<List<Node<Parcel>>> getAllPackages(String date) {
//        return this.parcelService.getPackages(date);
//    }
//

//
//    public UndirectedWeightedGraph getOptimalRoutePerRegion(String date) {
//        return this.parcelService.getDailyPackagesPerRegion(date);
//    }
//
//    public List<Region> getDeliveryRegions() {
//        return this.parcelService.getRegionsAsList();
//    }
//
//    public LinkedList<Vertex> getOptimalRouteBetween2Parcels(String date) {
//        var graph =  this.parcelService.getDailyPackagesPerRegion(date);
//        List<Vertex> vertices = graph.getVertices();
//        List<Edge> edges = graph.getEdges();
//        Dijkstra2 dijkstra = new Dijkstra2(vertices, edges);
//        dijkstra.execute(vertices.get(0));
//        return dijkstra.getPath(vertices.get(2));
//    }
//

    private HashMap<Long, List<Parcel>> getParcelsPerCustomer(Collection<Parcel> parcels) {
        return Searcher.getParcelsPerCustomer(parcels);
    }
//
//    private List<Parcel> getParcelsPerRegion(Collection<Parcel> parcels, Collection<Region> regions) {
//        var result = new ArrayList<List<Parcel>>();
//
//        for (var parcel : parcels) {
//            for (var region : regions) {
//                if (region.addressInRange(parcel.getClient().getAddress())) {
//                    regionParcels.add(parcel);
//                }
//            }
//
//        }
//    }
//
//    public void distributeParcels(List<Parcel> parcels) {
//        for (var parcel : parcels) {
//            for (var region : this.regions) {
//                if (region.addressInRange(parcel.getClient().getAddress())) {
//                    region.addParcel(parcel);
//                    break;
//                }
//            }
//        }
//    }
//
//    public List<List<Parcel>> getDailyPackagesPerRegion(Collection<Parcel> parcels, Collection<Region>) {
//        var result = new ArrayList<List<Parcel>>();
//        this.regionMap.distributeParcels(getParcelsForADay(date));
//
//        for (var region : this.regionMap.getRegions()) {
//            result.add(region.getDailyPackages());
//        }
//
//        return result;
//    }
}
