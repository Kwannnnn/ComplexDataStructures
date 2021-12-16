package util.tree;

import java.util.Comparator;

public class BinarySearchTree<T> {
    private Node<T> root;
    private Comparator<T> comparator;

    public BinarySearchTree(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public void insert(T data) {
        if (data != null) {
            this.root = insertNode(this.root, new Node<>(data));
        }
    }

    public boolean contains(T data) {
        return containsNode(this.root, data);
    }

    public Node<T> findNode(T data) {
        return searchForNode(this.root, data);
    }

    private Node<T> insertNode(Node<T> parent, Node<T> newNode) {
        // Base case
        if (parent == null) {
            return newNode;
        }

        // Step in the right direction
        var parentData = parent.getData();
        var newNodeData = newNode.getData();

        // Data of the new node is smaller than parent's data
        if (comparator.compare(newNodeData, parentData) < 0) {
            // Insert the new node to the left of the parent node
            parent.setLeft(insertNode(parent.getLeft(), newNode));
        } else if(comparator.compare(newNodeData, parentData) > 0) {
            // Insert the new node to the right of the parent node
            parent.setRight(insertNode(parent.getRight(), newNode));
        }

        return parent;
    }

    private boolean containsNode(Node<T> parent, T data) {
        // Base case 1: The parent node is empty
        if (parent == null) {
            return false;
        }
        // Base case 2: The parent node is the searched node
        var compareParentToData = comparator.compare(parent.getData(), data);
        if (compareParentToData == 0) {
            return true;
        }

        // Step in the right direction:
        // Check left: if data is smaller than the data of the parent
        // Check right: if data is larger than the data of the parent
        return compareParentToData < 0
                ? containsNode(parent.getLeft(), data)
                : containsNode(parent.getRight(), data);
    }

    private Node<T> searchForNode(Node<T> parent, T data) {
        // Base case 1: The parent node is empty
        if (parent == null) {
            return null;
        }
        // Base case 2: The parent node is the searched node
        var compareParentToData = comparator.compare(parent.getData(), data);
        if (compareParentToData == 0) {
            return parent;
        }

        // Step in the right direction:
        // Check left sub-tree: if data is smaller than the data of the parent
        // Check right sub-tree: if data is larger than the data of the parent
        return compareParentToData < 0
                ? searchForNode(parent.getLeft(), data)
                : searchForNode(parent.getRight(), data);
    }

}
