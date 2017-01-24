package com.lalitadithya.KDTree;

/**
 * Created by Lalit Adithya on 1/23/2017.
 */
public interface Space {

    Location getLocationOfPoint(Point point, int dimension);

    boolean isInside(Point point);

    int getNumberDimensions();

    enum Location {CANNOT_SAY, LESS_THAN, GREATER_THAN}
}
