package nl.saxion.cds.parcel;

import nl.saxion.cds.graph.UndirectedWeightedGraph;
import nl.saxion.cds.graph.Vertex;
import nl.saxion.cds.region.Coordinate;
import nl.saxion.cds.tree.Node;
import nl.saxion.cds.util.Packer;

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

    public UndirectedWeightedGraph getDailyPackagesPerRegion(String date) {
        var r1Packages = this.parcelDAO.getDailyPackagesPerRegion(date).get(0);
        var graph = new UndirectedWeightedGraph();
        var vDC = new Vertex("DC", new Coordinate(375,375));
        graph.addVertex(vDC);

        for (var parcel : r1Packages) {
            var v = new Vertex(parcel.getId().toString(), parcel.getClient().getAddress());
            graph.addVertex(v);
            for (var vertex : graph.getVertices()) {
                if (!v.equals(vertex)) {
                    graph.addEdge(v, vertex);
                }
            }

        }

        return graph;
    }
}
