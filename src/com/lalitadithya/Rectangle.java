package com.lalitadithya;

import com.lalitadithya.KDTree.Point;
import com.lalitadithya.KDTree.Space;

/**
 * Created by Lalit Adithya on 1/24/2017.
 */
public class Rectangle implements Space {
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
    public Location getLocationOfPoint(Point point, int dimension) {
        if (point.getNumberOfDimensions() != 2) {
            throw new IllegalArgumentException("Number of dimensions must be 2");
        }
        if (dimension < 0 || dimension > 1) {
            throw new IllegalArgumentException("Number of dimensions must be 2");
        }

        if (point.getNthDimension(dimension) > northEast.getNthDimension(dimension) && point.getNthDimension(dimension) > southWest.getNthDimension(dimension)) {
            return Location.LESS_THAN;
        } else if (point.getNthDimension(dimension) < northEast.getNthDimension(dimension) && point.getNthDimension(dimension) < southWest.getNthDimension(dimension)) {
            return Location.GREATER_THAN;
        }
        return Location.CANNOT_SAY;
    }

    @Override
    public int getNumberDimensions() {
        return NUMBER_DIMENSIONS;
    }
}
