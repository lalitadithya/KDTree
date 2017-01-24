package com.lalitadithya;

import com.lalitadithya.KDTree.IPoint;
import com.lalitadithya.KDTree.ISpace;

/**
 * Created by Lalit Adithya on 1/24/2017.
 */
public class Rectangle implements ISpace {
    private static final int NUMBER_DIMENSIONS = 2;
    private PointIn2DimensionalSpace northEast;
    private PointIn2DimensionalSpace southWest;

    public Rectangle(PointIn2DimensionalSpace northEast, PointIn2DimensionalSpace southWest) {
        this.northEast = northEast;
        this.southWest = southWest;
    }

    public PointIn2DimensionalSpace getNorthEast() {
        return northEast;
    }

    public void setNorthEast(PointIn2DimensionalSpace northEast) {
        this.northEast = northEast;
    }

    public PointIn2DimensionalSpace getSouthWest() {
        return southWest;
    }

    public void setSouthWest(PointIn2DimensionalSpace southWest) {
        this.southWest = southWest;
    }

    @Override
    public Location getLocationOfPoint(IPoint IPoint, int dimension) {
        if (IPoint.getNumberOfDimensions() != 2) {
            throw new IllegalArgumentException("Number of dimensions must be 2");
        }
        if (dimension < 0 || dimension > 1) {
            throw new IllegalArgumentException("Number of dimensions must be 2");
        }

        if (IPoint.getNthDimension(dimension) > northEast.getNthDimension(dimension) && IPoint.getNthDimension(dimension) > southWest.getNthDimension(dimension)) {
            return Location.WEST;
        } else if (IPoint.getNthDimension(dimension) < northEast.getNthDimension(dimension) && IPoint.getNthDimension(dimension) < southWest.getNthDimension(dimension)) {
            return Location.EAST;
        }
        return Location.CANNOT_SAY;
    }

    @Override
    public boolean isInside(IPoint IPoint) {
        if (IPoint == null) {
            throw new NullPointerException("IPoint can not be null");
        }

        if (IPoint.getNumberOfDimensions() != 2) {
            throw new IllegalArgumentException("Number of dimensions must be 2");
        }

        return IPoint.getNthDimension(0) >= southWest.getNthDimension(0) &&
                IPoint.getNthDimension(0) <= northEast.getNthDimension(0) &&
                IPoint.getNthDimension(1) >= southWest.getNthDimension(1) &&
                IPoint.getNthDimension(1) <= northEast.getNthDimension(1);

    }

    @Override
    public int getNumberDimensions() {
        return NUMBER_DIMENSIONS;
    }
}
