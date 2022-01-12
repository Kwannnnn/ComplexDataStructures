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
        var width = data.getWidth();
        var node = this.findNode(data.getWidth(), data.getLength());
        if (node == null) return false;

        node.setData(data);
        node.setUsed(true);
        node.setDown(new Node<>(node.getX(), node.getY() + length, node.getWidth(), node.getLength() - length));
        node.setRight(new Node<>(node.getX() + width, node.getY(), node.getWidth() - width, length));

        return true;
    }

    public Node<Parcel> findNode(double width, double length) {
        return searchForNode(this.root, width, length);
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

    private Node<Parcel> searchForNode(Node<Parcel> parent, double width, double length) {
        // Base case 1: The parent node is empty
        if (parent == null) {
            return null;
        }

        // Base case 2: The parent node is the search
        if (width <= parent.getWidth() && length <= parent.getLength() && !parent.isUsed()) {
            return parent;
        }

        // Step in the right direction:
        // Check left sub-tree: if data is smaller than the data of the parent
        // Check right sub-tree: if data is larger than the data of the parent
        var right = searchForNode(parent.getRight(), width, length);
        if (right == null) return searchForNode(parent.getDown(), width, length);

        return right;
    }
}
