package com.lalitadithya.KDTree;

import java.util.LinkedList;
import java.util.List;

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
            throw new NullPointerException("Space can not be null");
        }

        if (point.getNumberOfDimensions() != numberOfDimensions) {
            throw new IllegalArgumentException("Number of dimensions must be " + numberOfDimensions);
        }

        return _searchSingle(rootNode, point, 0);
    }

    public List<T> searchRange(Space space) {
        if (space == null) {
            throw new NullPointerException("Space can not be null");
        }

        if (space.getNumberDimensions() != numberOfDimensions) {
            throw new IllegalArgumentException("Number of dimensions must be " + numberOfDimensions);
        }

        List<T> result = new LinkedList<>();
        _searchRange(rootNode, space, 0, result);

        return result;
    }

    public double findMinimum(int dimension) {
        if (dimension < 0 || dimension >= numberOfDimensions) {
            throw new IllegalArgumentException("dimension must be between 0 and " + numberOfDimensions);
        }

        return _findMinimum(rootNode, dimension, 0).getPoint().getNthDimension(dimension);
    }

    public void delete(Point point) {
        if (point == null) {
            throw new NullPointerException("point can not be null");
        }

        rootNode = _delete(rootNode, point, 0);
        size--;
    }

    public void visualize() {
        _visualize(rootNode, 0);
    }

    private void _visualize(Node<T> node, int numberOfSpaces) {
        if (node == null) {
            System.out.println();
            return;
        }
        for (int i = 0; i < numberOfSpaces; i++) {
            System.out.print(" ");
        }
        System.out.println(node.getObject() + "[" + node.getPoint() + "]");
        _visualize(node.getLeftChild(), numberOfSpaces + 2);
        _visualize(node.getRightChild(), numberOfSpaces + 2);
    }

    private Node<T> _delete(Node<T> node, Point point, int currentDimension) {
        if (node == null) {
            return null;
        }

        if (node.getPoint().equals(point)) {
            if (node.getRightChild() != null) {
                Node<T> minimum = _findMinimum(node.getRightChild(), currentDimension, 0);

                node.setPoint(minimum.getPoint());
                node.setObject(minimum.getObject());

                node.setRightChild(_delete(node.getRightChild(), minimum.getPoint(), (currentDimension + 1) % numberOfDimensions));
            } else if (node.getLeftChild() != null) {
                Node<T> minimum = _findMinimum(node.getLeftChild(), currentDimension, 0);

                node.setPoint(minimum.getPoint());
                node.setObject(minimum.getObject());

                node.setRightChild(_delete(node.getLeftChild(), minimum.getPoint(), (currentDimension + 1) % numberOfDimensions));
            } else {
                node = null;
                return null;
            }
            return node;
        }

        if (node.getPoint().getNthDimension(currentDimension) > point.getNthDimension(currentDimension)) {
            node.setLeftChild(_delete(node.getLeftChild(), point, (currentDimension + 1) % numberOfDimensions));
        } else {
            node.setRightChild(_delete(node.getRightChild(), point, (currentDimension + 1) % numberOfDimensions));
        }

        return node;
    }

    private Node<T> _findMinimum(Node<T> node, int dimension, int currentDimension) {
        if (node == null) {
            return null;
        }

        if (currentDimension == dimension) {
            if (node.getLeftChild() == null) {
                return node;
            }

            return _findMinimum(node.getLeftChild(), dimension, (currentDimension + 1) % numberOfDimensions);
        }

        double currentDimensionValue = node.getPoint().getNthDimension(dimension);
        Node<T> leftTreeDimensionValue = _findMinimum(node.getLeftChild(), dimension, (currentDimension + 1) % numberOfDimensions);
        Node<T> rightTreeDimensionValue = _findMinimum(node.getRightChild(), dimension, (currentDimension + 1) % numberOfDimensions);

        double min = 0.0;
        if (leftTreeDimensionValue != null && rightTreeDimensionValue != null) {
            min = Double.min(currentDimensionValue, Double.min(leftTreeDimensionValue.getPoint().getNthDimension(dimension), rightTreeDimensionValue.getPoint().getNthDimension(dimension)));
        } else if (leftTreeDimensionValue != null) {
            min = Double.min(currentDimensionValue, leftTreeDimensionValue.getPoint().getNthDimension(dimension));
        } else if (rightTreeDimensionValue != null) {
            min = Double.min(currentDimensionValue, rightTreeDimensionValue.getPoint().getNthDimension(dimension));
        }

        if (min == currentDimensionValue) {
            return node;
        } else if ((leftTreeDimensionValue != null) && (min == leftTreeDimensionValue.getPoint().getNthDimension(dimension))) {
            return leftTreeDimensionValue;
        } else if ((rightTreeDimensionValue != null) && (min == rightTreeDimensionValue.getPoint().getNthDimension(dimension))) {
            return rightTreeDimensionValue;
        }
        return node;
    }

    private void _searchRange(Node<T> node, Space space, int currentDimension, List<T> result) {
        if (node != null) {
            if (space.isInside(node.getPoint())) {
                result.add(node.getObject());
            }

            switch (space.getLocationOfPoint(node.getPoint(), currentDimension)) {
                case GREATER_THAN:
                    _searchRange(node.getRightChild(), space, (currentDimension + 1) % numberOfDimensions, result);
                    break;
                case LESS_THAN:
                    _searchRange(node.getLeftChild(), space, (currentDimension + 1) % numberOfDimensions, result);
                    break;
                case CANNOT_SAY:
                    _searchRange(node.getLeftChild(), space, (currentDimension + 1) % numberOfDimensions, result);
                    _searchRange(node.getRightChild(), space, (currentDimension + 1) % numberOfDimensions, result);
                    break;
            }
        }
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
