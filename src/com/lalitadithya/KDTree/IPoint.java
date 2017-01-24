package com.lalitadithya.KDTree;

import java.util.List;

/**
 * An interface to represent a point in n dimensions
 */
public interface IPoint {
    /**
     * Method to get the nth dimension in the n dimensional space
     *
     * @param n the dimension to get
     * @return the value of the dimension
     */
    double getNthDimension(int n);

    /**
     * Method to return all the values of the dimensions as a list
     * @return a list containing the values of all the dimensions
     */
    List<Double> getDimensions();

    /**
     * Method to get the value of n
     * @return the value of n
     */
    int getNumberOfDimensions();

    /**
     * Method to check if two points are the same
     *
     * @param obj the other point
     * @return true, if the points are the same, false otherwise
     */
    boolean equals(IPoint obj);
}
