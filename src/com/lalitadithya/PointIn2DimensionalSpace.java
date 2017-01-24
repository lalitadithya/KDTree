package com.lalitadithya;

import com.lalitadithya.KDTree.IPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lalit Adithya on 1/23/2017.
 */
public class PointIn2DimensionalSpace implements IPoint {
    private static final int NUMBER_OF_DIMENSIONS = 2;
    private List<Double> dimensions;

    public PointIn2DimensionalSpace() {
        dimensions = new ArrayList<>(2);
    }

    @Override
    public double getNthDimension(int n) {
        if (n > dimensions.size() || n < 0) {
            throw new ArrayIndexOutOfBoundsException(n + " dimension does not exist");
        }

        return dimensions.get(n);
    }

    public void setNthDimension(int n, double value) {
        if (n > dimensions.size() || n < 0) {
            throw new ArrayIndexOutOfBoundsException(n + " dimension does not exist");
        }

        dimensions.set(n, value);
    }

    public void initialize(List<Double> dimensions) {
        if (dimensions.size() != 2) {
            throw new IllegalArgumentException("Only two dimensions are supported");
        }

        this.dimensions = new ArrayList<>(dimensions);
    }

    @Override
    public List<Double> getDimensions() {
        return new ArrayList<>(this.dimensions);
    }

    @Override
    public int getNumberOfDimensions() {
        return NUMBER_OF_DIMENSIONS;
    }

    public boolean equals(IPoint other) {
        return this.getDimensions().equals(other.getDimensions());
    }

    @Override
    public String toString() {
        return "(" + dimensions.get(0) + ", " + dimensions.get(1) + ")";
    }
}
