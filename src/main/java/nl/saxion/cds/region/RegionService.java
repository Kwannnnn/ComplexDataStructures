package nl.saxion.cds.region;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegionService {
    private final RegionMap regionMap;

    public RegionService(RegionMap regionMap) {
        this.regionMap = regionMap;
    }

    public List<Region> getAllRegions() {
        ArrayList<Region> result = new ArrayList<>();
        Collections.addAll(result, regionMap.getRegions());
        return result;
    }

    public int getRegionMapWidth() {
        return regionMap.getWidth();
    }

    public int getRegionMapLength() {
        return regionMap.getLength();
    }

    public Coordinate getRegionMapTopLeft() {
        return regionMap.getTopLeft();
    }

    public Coordinate getRegionMapTopRight() {
        return regionMap.getTopRight();
    }

    public Coordinate getRegionMapBottomLeft() {
        return regionMap.getBottomLeft();
    }

    public Coordinate getRegionMapBottomRight() {
        return regionMap.getBottomRight();
    }
}
