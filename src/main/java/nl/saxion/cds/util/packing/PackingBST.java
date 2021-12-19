/*
 * Copyright (c) 2017, alexbonilla
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package nl.saxion.cds.util.packing;

import nl.saxion.cds.parcel.Parcel;

/**
 *
 * @author alexbonilla
 */
public class PackingBST implements BinarySearchTree<Parcel> {

    private final Node<Parcel> root;

    public PackingBST(int w, int h) {
        this.root = new Node<>(0, 0, w, h);
    }

    @Override
    public void insert(Parcel data) {
        var length = data.getLength();
        var breadth = data.getBreadth();
        var node = this.findNode(data.getLength(), data.getBreadth());
        if (node == null) return;

        node.setData(data);
        node.setUsed(true);
        node.setDown(new Node<>(node.getX(), node.getY() + length, node.getW(), node.getH() - length));
        node.setRight(new Node<>(node.getX() + breadth, node.getY(), node.getW() - breadth, length));
    }

    public Node<Parcel> findNode(double w, double h) {
        return searchForNode(this.root, w, h);
    }

    private Node<Parcel> searchForNode(Node<Parcel> parent, double w, double h) {
        // Base case 1: The parent node is empty
        if (parent == null) {
            return null;
        }

        // Base case 2: The parent node is the search
        if (w <= parent.getW() && h <= parent.getH()) {
            return parent;
        }

        // Step in the right direction:
        // Check left sub-tree: if data is smaller than the data of the parent
        // Check right sub-tree: if data is larger than the data of the parent
        var right = searchForNode(parent.getRight(), w, h);
        if (right == null) return searchForNode(parent.getDown(), w, h);

        return right;
    }

//    public void fit(List<Parcel> parcels) {
//        Node<Parcel> node;
//        Parcel parcel;
//        Iterator<Parcel> parcelIterator = parcels.iterator();
//        while (parcelIterator.hasNext()) {
//            parcel = parcelIterator.next();
//            if ((node = this.findNode(parcel.getW(), parcel.getH()))!=null) {
//                // set value of
//                parcel.setData(this.splitNode(node, parcel.getW(), parcel.getH()));
//                if(node.isRoot()){
//                    parcel.getData().setRoot(true);
//                }
//            }
//        }
//    }

//        if (root.isUsed()) {
//            Node<Parcel> right = findNode(root.getRight(), w, h);
//            return (right != null ? right : findNode(root.getDown(), w, h));
//        } else if ((w <= root.getW()) && (h <= root.getH())) {
//            return root;
//        } else {
//            return null;
//        }
//    }



//    public void splitNode(Node<Parcel> node, int w, int h) {
//        node.setUsed(true);
//        node.setDown(new Node<>(node.getX(), node.getY() + h, node.getW(), node.getH() - h));
//        node.setRight(new Node<>(node.getX() + w, node.getY(), node.getW() - w, h));
//    }

}
