package nl.saxion.cds.util.tree;

public class Node<T> {
    private final int x;
    private final int y;
    private final int width;
    private final int length;
    private boolean used = false;
    private Node<T> right;
    private Node<T> down;
    private T data;

    public Node(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.length = h;
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

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
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
