package nl.saxion.cds.region;

import nl.saxion.cds.parcel.Parcel;

import java.util.List;

public class RegionMap {
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    private Region[] regions;

    public RegionMap(int x1, int y1, int x2, int y2, int regionsCount) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        splitRegions(regionsCount);
    }

    private void splitRegions(int regionsCount) {
        this.regions = new Region[regionsCount];
        int nrRegions = regions.length;
        double startX = x1;
        double startY = y1;
        double regionSizeX = (x2 - x1) / Math.ceil(nrRegions / 2.);
        double regionSizeY = (y2 - y1) / 2.;

        int startIndex = 0;
        if (nrRegions % 2 == 1) {
            this.regions[0] = new Region(new Coordinate(x1, y1),
                    new Coordinate(x1 + regionSizeX, y1),
                    new Coordinate(x1, y1 + 2 * regionSizeY),
                    new Coordinate(x1 + regionSizeX, y1 + 2 * regionSizeY));
            startIndex = 1;
            startX += regionSizeX;
        }

        for (int i = startIndex; i < nrRegions; i++) {
            if (i == Math.ceil(nrRegions / 2.)) {
                startX = x1 + regionSizeX;
                startY += regionSizeY;
            }
            this.regions[i] = new Region(new Coordinate(startX, startY),
                    new Coordinate(startX + regionSizeX, startY),
                    new Coordinate(startX, startY + regionSizeY),
                    new Coordinate(startX + regionSizeX, startY + regionSizeY));

            startX += regionSizeX;
        }
    }

    public Region[] getRegions() {
        return regions;
    }

    public int getWidth() {
        return x2 - x1;
    }

    public int getLength() {
        return y2 - y1;
    }

    public Coordinate getTopLeft() {
        return new Coordinate(x1, y1);
    }

    public Coordinate getTopRight() {
        return new Coordinate(x2, y1);
    }

    public Coordinate getBottomLeft() {
        return new Coordinate(x1, y2);
    }

    public Coordinate getBottomRight() {
        return new Coordinate(x2, y2);
    }
}
