package nl.saxion.cds.van;

import nl.saxion.cds.tree.PackingBST;

public class Van {
    private int length;
    private int width;
    private int availableArea;
    private PackingBST placement;

    public Van(int length, int width) {
        this.length = length;
        this.width = width;
        this.availableArea = length * width;
        this.placement = new PackingBST(length, width);
    }

    public PackingBST getPlacement() {
        return this.placement;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return this.length;
    }
}
