package nl.saxion.cds.util.tree;

public class Node<T> {
    private boolean isRoot = false;
    private String name;
    private int x;
    private int y;
    private int w;
    private int h;
    private boolean used = false;
    private Node<T> right;
    private Node<T> down;
    private T data;

    public Node(String name, int w, int h) {
        this.name = name;
        this.w = w;
        this.h = h;
    }

    public Node(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        if(x == 0 && y == 0){
            this.isRoot = true;//this is only necessary for me to print 'Pack Starts Here' in the example code
        }
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

    public boolean isRoot() {
        return isRoot;
    }

    public String getName() {
        return name;
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
