package com.lalitadithya;

import com.lalitadithya.KDTree.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lalit Adithya on 1/23/2017.
 */
public class PointIn2DimensionalSpace implements Point {
    private List<Double> dimensions;
    private int numberOfDimensions;

    public PointIn2DimensionalSpace() {
        numberOfDimensions = 0;
        dimensions = new ArrayList<>();
    }

    @Override
    public double getNthDimension(int n) {
        if (n > dimensions.size() || n < 0) {
            throw new ArrayIndexOutOfBoundsException(n + " dimension does not exist");
        }

        return dimensions.get(n);
    }

    @Override
    public void setNthDimension(int n, double value) {
        if (n > dimensions.size() || n < 0) {
            throw new ArrayIndexOutOfBoundsException(n + " dimension does not exist");
        }

        dimensions.set(n, value);
    }

    @Override
    public void initialize(List<Double> dimensions) {
        if (dimensions.size() != 2) {
            throw new IllegalArgumentException("Only two dimensions are supported");
        }

        this.dimensions = new ArrayList<>(dimensions);
        numberOfDimensions = this.dimensions.size();
    }

    @Override
    public List<Double> getDimensions() {
        return new ArrayList<>(this.dimensions);
    }

    @Override
    public int getNumberOfDimensions() {
        return numberOfDimensions;
    }

    public boolean equals(Point other) {
        return this.getDimensions().equals(other.getDimensions());
    }
}