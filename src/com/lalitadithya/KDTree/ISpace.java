package com.lalitadithya.KDTree;

/**
 * An interface to represent some region in n dimensions
 */
public interface ISpace {

    /**
     * Method to find the location of a point relative to this space, wrt a dimension
     *
     * @param IPoint    the point
     * @param dimension the dimensoion
     * @return the relative location
     */
    Location getLocationOfPoint(IPoint IPoint, int dimension);

    /**
     * Method to check if the point in inside the given space
     *
     * @param IPoint the point
     * @return true, if the point is inside the given space, false otherwise
     */
    boolean isInside(IPoint IPoint);

    /**
     * Method to get n
     * @return the value of n
     */
    int getNumberDimensions();

    /**
     * enum to represent the relative location of one object wrt to another
     */
    enum Location {
        CANNOT_SAY, WEST, EAST}
}
