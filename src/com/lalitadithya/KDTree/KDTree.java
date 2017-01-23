package com.lalitadithya.KDTree;

/**
 * Created by Lalit Adithya on 1/23/2017.
 */
public class KDTree<T> {
    private Node<T> rootNode;
    private int numberOfDimensions;
    private int size;

    public KDTree(int numberOfDimensions) {
        rootNode = null;
        this.numberOfDimensions = numberOfDimensions;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void insertNode(T object, Point point) {
        Node<T> toInsert = new Node<>(object, point);
        if (point.getNumberOfDimensions() != numberOfDimensions) {
            throw new IllegalArgumentException("number of dimensions must be " + numberOfDimensions);
        }

        rootNode = _insertNode(rootNode, toInsert, 0);
        size++;
    }

    public T searchSingle(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Point can not be null");
        }

        if (point.getNumberOfDimensions() != numberOfDimensions) {
            throw new IllegalArgumentException("Number of dimensions must be " + numberOfDimensions);
        }

        return _searchSingle(rootNode, point, 0);
    }

    private T _searchSingle(Node<T> node, Point point, int currentDimension) {
        if (node == null) {
            return null;
        } else if (node.getPoint().equals(point)) {
            return node.getObject();
        } else if (node.getPoint().getNthDimension(currentDimension) > point.getNthDimension(currentDimension)) {
            return _searchSingle(node.getLeftChild(), point, (currentDimension + 1) % numberOfDimensions);
        } else {
            return _searchSingle(node.getRightChild(), point, (currentDimension + 1) % numberOfDimensions);
        }
    }

    private Node<T> _insertNode(Node<T> node, Node<T> toInsert, int currentDimension) {
        if (node == null) {
            return toInsert;
        }

        if (node.getPoint().getNthDimension(currentDimension) > toInsert.getPoint().getNthDimension(currentDimension)) {
            node.setLeftChild(_insertNode(node.getLeftChild(), toInsert, (currentDimension + 1) % numberOfDimensions));
        } else {
            node.setRightChild(_insertNode(node.getRightChild(), toInsert, (currentDimension + 1) % numberOfDimensions));
        }

        return node;
    }

    public boolean isEmpty() {
        return rootNode == null;
    }
}
