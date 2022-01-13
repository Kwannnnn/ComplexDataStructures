package nl.saxion.cds.region;

public class Coordinate {
    private double x;
    private double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ";" + y + ")";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
