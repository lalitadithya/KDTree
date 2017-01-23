package com.lalitadithya.KDTree;

/**
 * Created by Lalit Adithya on 1/23/2017.
 */
class Node<T> {
    private T object;
    private Point point;
    private Node<T> leftChild, rightChild;

    Node(T object, Point point) {
        this.object = object;
        this.point = point;
        leftChild = rightChild = null;
    }

    Point getPoint() {
        return point;
    }

    void setPoint(Point point) {
        this.point = point;
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
