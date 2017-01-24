package com.lalitadithya.KDTree;

/**
 * A class that represents a node in the KD Tree
 * @param <T> the type of object that the node stores
 */
class Node<T> {
    private T object;
    private IPoint IPoint;
    private Node<T> leftChild, rightChild;

    Node(T object, IPoint IPoint) {
        this.object = object;
        this.IPoint = IPoint;
        leftChild = rightChild = null;
    }

    IPoint getIPoint() {
        return IPoint;
    }

    void setIPoint(IPoint IPoint) {
        this.IPoint = IPoint;
    }

    T getObject() {
        return object;
    }

    void setObject(T object) {
        this.object = object;
    }

    Node<T> getLeftChild() {
        return leftChild;
    }

    void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    Node<T> getRightChild() {
        return rightChild;
    }

    void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }
}
