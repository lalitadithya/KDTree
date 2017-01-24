package com.lalitadithya.KDTree;

import java.util.List;

/**
 * Created by Lalit Adithya on 1/23/2017.
 */
public interface Point {
    double getNthDimension(int n);

    void setNthDimension(int n, double value);

    List<Double> getDimensions();

    int getNumberOfDimensions();

    boolean equals(Point obj);
}
