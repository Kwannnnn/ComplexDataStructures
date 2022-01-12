package nl.saxion.cds.util.tree;

public class Node<T> {
    private final int x;
    private final int y;
    private final int w;
    private final int h;
    private boolean used = false;
    private Node<T> right;
    private Node<T> down;
    private T data;

    public Node(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public void setDown(Node<T> down) {
        this.down = down;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public boolean isUsed() {
        return used;
    }

    public Node<T> getRight() {
        return right;
    }

    public Node<T> getDown() {
        return down;
    }

    public T getData() {
        return this.data;
    }
}
