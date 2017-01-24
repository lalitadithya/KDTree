package com.lalitadithya.KDTree;

import java.util.LinkedList;
import java.util.List;

/**
 * A class that represents KD Tree
 * @param <T> the object that must ne stored
 */
public class KDTree<T> {
    private Node<T> rootNode;
    private int numberOfDimensions;
    private int size;

    /**
     * Constructor to set the initial values
     *
     * @param numberOfDimensions the value of k
     */
    public KDTree(int numberOfDimensions) {
        rootNode = null;
        this.numberOfDimensions = numberOfDimensions;
        size = 0;
    }

    /**
     * Getter method to get the size of the tree
     * @return the size of the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Method to insert a node
     *
     * @param object the object to be inserted
     * @param IPoint the IPoint in space corresponding to the object
     */
    public void insertNode(T object, IPoint IPoint) {
        Node<T> toInsert = new Node<>(object, IPoint);
        if (IPoint.getNumberOfDimensions() != numberOfDimensions) {
            throw new IllegalArgumentException("number of dimensions must be " + numberOfDimensions);
        }

        rootNode = _insertNode(rootNode, toInsert, 0);
        size++;
    }

    /**
     * Method to search for a single IPoint in the KD tree
     *
     * @param IPoint the IPoint to be found
     * @return the object at the IPoint, if found, else null
     */
    public T searchSingle(IPoint IPoint) {
        if (IPoint == null) {
            throw new NullPointerException("ISpace can not be null");
        }

        if (IPoint.getNumberOfDimensions() != numberOfDimensions) {
            throw new IllegalArgumentException("Number of dimensions must be " + numberOfDimensions);
        }

        return _searchSingle(rootNode, IPoint, 0);
    }

    /**
     * Method to find all the objects in a given ISpace
     *
     * @param ISpace the region to search in
     * @return a list of objects found in the region
     */
    public List<T> searchRange(ISpace ISpace) {
        if (ISpace == null) {
            throw new NullPointerException("ISpace can not be null");
        }

        if (ISpace.getNumberDimensions() != numberOfDimensions) {
            throw new IllegalArgumentException("Number of dimensions must be " + numberOfDimensions);
        }

        List<T> result = new LinkedList<>();
        _searchRange(rootNode, ISpace, 0, result);

        return result;
    }

    /**
     * Method to find the minimum value in the a given dimension
     * @param dimension the dimension in which the minimum value has to be found
     * @return the minimum value
     */
    public double findMinimum(int dimension) {
        if (dimension < 0 || dimension >= numberOfDimensions) {
            throw new IllegalArgumentException("dimension must be between 0 and " + numberOfDimensions);
        }

        return _findMinimum(rootNode, dimension, 0).getIPoint().getNthDimension(dimension);
    }

    /**
     * Method to delete a IPoint
     *
     * @param IPoint the IPoint to be deleted
     */
    public void delete(IPoint IPoint) {
        if (IPoint == null) {
            throw new NullPointerException("IPoint can not be null");
        }

        rootNode = _delete(rootNode, IPoint, 0);
        size--;
    }

    /**
     * Method to visualize the KD tree
     */
    public void visualize() {
        _visualize(rootNode, 0);
    }

    /**
     * Method to get if the tree is empty
     *
     * @return true, if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return rootNode == null;
    }

    /**
     * Helper method to visualize the tree
     * @param node the node from which the visualization has to start
     * @param numberOfSpaces the number of blank spaces that have to be left before printing starts
     */
    private void _visualize(Node<T> node, int numberOfSpaces) {
        if (node == null) {
            System.out.println();
            return;
        }
        for (int i = 0; i < numberOfSpaces; i++) {
            System.out.print(" ");
        }
        System.out.println(node.getObject() + "[" + node.getIPoint() + "]");
        _visualize(node.getLeftChild(), numberOfSpaces + 2);
        _visualize(node.getRightChild(), numberOfSpaces + 2);
    }

    /**
     * Helper method to delete a IPoint
     *
     * @param node             the node to search from
     * @param IPoint           the IPoint to be deleted
     * @param currentDimension the dimension, that is, X or Y of the current node
     * @return a node
     */
    private Node<T> _delete(Node<T> node, IPoint IPoint, int currentDimension) {
        if (node == null) {
            return null;
        }

        if (node.getIPoint().equals(IPoint)) {
            if (node.getRightChild() != null) {
                Node<T> minimum = _findMinimum(node.getRightChild(), currentDimension, 0);

                node.setIPoint(minimum.getIPoint());
                node.setObject(minimum.getObject());

                node.setRightChild(_delete(node.getRightChild(), minimum.getIPoint(), (currentDimension + 1) % numberOfDimensions));
            } else if (node.getLeftChild() != null) {
                Node<T> minimum = _findMinimum(node.getLeftChild(), currentDimension, 0);

                node.setIPoint(minimum.getIPoint());
                node.setObject(minimum.getObject());

                node.setRightChild(_delete(node.getLeftChild(), minimum.getIPoint(), (currentDimension + 1) % numberOfDimensions));
            } else {
                node = null;
                return null;
            }
            return node;
        }

        if (node.getIPoint().getNthDimension(currentDimension) > IPoint.getNthDimension(currentDimension)) {
            node.setLeftChild(_delete(node.getLeftChild(), IPoint, (currentDimension + 1) % numberOfDimensions));
        } else {
            node.setRightChild(_delete(node.getRightChild(), IPoint, (currentDimension + 1) % numberOfDimensions));
        }

        return node;
    }

    /**
     * Helper method to find the minimum node
     * @param node the node to search from
     * @param dimension the dimension whos minimum is required
     * @param currentDimension the dimension of the current node
     * @return a node
     */
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

        double currentDimensionValue = node.getIPoint().getNthDimension(dimension);
        Node<T> leftTreeDimensionValue = _findMinimum(node.getLeftChild(), dimension, (currentDimension + 1) % numberOfDimensions);
        Node<T> rightTreeDimensionValue = _findMinimum(node.getRightChild(), dimension, (currentDimension + 1) % numberOfDimensions);

        double min = 0.0;
        if (leftTreeDimensionValue != null && rightTreeDimensionValue != null) {
            min = Double.min(currentDimensionValue, Double.min(leftTreeDimensionValue.getIPoint().getNthDimension(dimension), rightTreeDimensionValue.getIPoint().getNthDimension(dimension)));
        } else if (leftTreeDimensionValue != null) {
            min = Double.min(currentDimensionValue, leftTreeDimensionValue.getIPoint().getNthDimension(dimension));
        } else if (rightTreeDimensionValue != null) {
            min = Double.min(currentDimensionValue, rightTreeDimensionValue.getIPoint().getNthDimension(dimension));
        }

        if (min == currentDimensionValue) {
            return node;
        } else if ((leftTreeDimensionValue != null) && (min == leftTreeDimensionValue.getIPoint().getNthDimension(dimension))) {
            return leftTreeDimensionValue;
        } else if ((rightTreeDimensionValue != null) && (min == rightTreeDimensionValue.getIPoint().getNthDimension(dimension))) {
            return rightTreeDimensionValue;
        }
        return node;
    }

    /**
     * Helper method to find all points in a given region
     *
     * @param node             the node to search from
     * @param ISpace           the region
     * @param currentDimension the dimension of the current node
     * @param result           the result list
     */
    private void _searchRange(Node<T> node, ISpace ISpace, int currentDimension, List<T> result) {
        if (node != null) {
            if (ISpace.isInside(node.getIPoint())) {
                result.add(node.getObject());
            }

            switch (ISpace.getLocationOfPoint(node.getIPoint(), currentDimension)) {
                case EAST:
                    _searchRange(node.getRightChild(), ISpace, (currentDimension + 1) % numberOfDimensions, result);
                    break;
                case WEST:
                    _searchRange(node.getLeftChild(), ISpace, (currentDimension + 1) % numberOfDimensions, result);
                    break;
                case CANNOT_SAY:
                    _searchRange(node.getLeftChild(), ISpace, (currentDimension + 1) % numberOfDimensions, result);
                    _searchRange(node.getRightChild(), ISpace, (currentDimension + 1) % numberOfDimensions, result);
                    break;
            }
        }
    }

    /**
     * Helper method to find a single IPoint
     * @param node the node to search from
     * @param IPoint the IPoint to find
     * @param currentDimension the dimension of the current node
     * @return the object corresponding to the IPoint
     */
    private T _searchSingle(Node<T> node, IPoint IPoint, int currentDimension) {
        if (node == null) {
            return null;
        } else if (node.getIPoint().equals(IPoint)) {
            return node.getObject();
        } else if (node.getIPoint().getNthDimension(currentDimension) > IPoint.getNthDimension(currentDimension)) {
            return _searchSingle(node.getLeftChild(), IPoint, (currentDimension + 1) % numberOfDimensions);
        } else {
            return _searchSingle(node.getRightChild(), IPoint, (currentDimension + 1) % numberOfDimensions);
        }
    }

    /**
     * Helper method to insert a node
     * @param node the root node
     * @param toInsert the node to insert
     * @param currentDimension the dimension of the current node
     * @return the new root node
     */
    private Node<T> _insertNode(Node<T> node, Node<T> toInsert, int currentDimension) {
        if (node == null) {
            return toInsert;
        }

        if (node.getIPoint().getNthDimension(currentDimension) > toInsert.getIPoint().getNthDimension(currentDimension)) {
            node.setLeftChild(_insertNode(node.getLeftChild(), toInsert, (currentDimension + 1) % numberOfDimensions));
        } else {
            node.setRightChild(_insertNode(node.getRightChild(), toInsert, (currentDimension + 1) % numberOfDimensions));
        }

        return node;
    }
}
