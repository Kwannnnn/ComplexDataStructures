package nl.saxion.cds.util.packing;

import nl.saxion.cds.parcel.Parcel;
import nl.saxion.cds.util.tree.BinarySearchTree;
import nl.saxion.cds.util.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class PackingBST implements BinarySearchTree<Parcel> {

    private final Node<Parcel> root;

    public PackingBST(int w, int h) {
        this.root = new Node<>(0, 0, w, h);
    }

    @Override
    public boolean insert(Parcel data) {
        var length = data.getLength();
        var breadth = data.getBreadth();
        var node = this.findNode(data.getLength(), data.getBreadth());
        if (node == null) return false;

        node.setData(data);
        node.setUsed(true);
        node.setDown(new Node<>(node.getX(), node.getY() + length, node.getW(), node.getH() - length));
        node.setRight(new Node<>(node.getX() + breadth, node.getY(), node.getW() - breadth, length));

        return true;
    }

    public Node<Parcel> findNode(double w, double h) {
        return searchForNode(this.root, w, h);
    }
    
    public List<Node<Parcel>> toList() {
        System.out.println(this.root.getRight().getData().getLength());
        return createList(this.root);
    }

    private List<Node<Parcel>> createList(Node<Parcel> root) {
        if(root == null) {
            return new ArrayList<>();
        }

        var list = new ArrayList<Node<Parcel>>();
        list.addAll(createList(root.getRight()));
        list.add(root);
        list.addAll(createList(root.getDown()));

        return list;
    }

    private Node<Parcel> searchForNode(Node<Parcel> parent, double w, double h) {
        // Base case 1: The parent node is empty
        if (parent == null) {
            return null;
        }

        // Base case 2: The parent node is the search
        if (w <= parent.getW() && h <= parent.getH() && !parent.isUsed()) {
            return parent;
        }

        // Step in the right direction:
        // Check left sub-tree: if data is smaller than the data of the parent
        // Check right sub-tree: if data is larger than the data of the parent
        var right = searchForNode(parent.getRight(), w, h);
        if (right == null) return searchForNode(parent.getDown(), w, h);

        return right;
    }
}
