package com.lalitadithya.test;

import com.lalitadithya.KDTree.Space;
import com.lalitadithya.PointIn2DimensionalSpace;
import com.lalitadithya.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Lalit Adithya on 1/24/2017.
 */
class RectangleTest {

    private PointIn2DimensionalSpace getPointObject(double[] values) {
        PointIn2DimensionalSpace myPoint = new PointIn2DimensionalSpace();
        ArrayList<Double> point = new ArrayList<Double>();
        for (double value : values) {
            point.add(value);
        }
        myPoint.initialize(point);
        return myPoint;
    }

    @Test
    void getNorthEast() {
        Rectangle rectangle = new Rectangle(getPointObject(new double[]{1.0, 2.0}), getPointObject(new double[]{1.0, 2.0}));
        PointIn2DimensionalSpace myPoint = getPointObject(new double[]{9.0, 2.0});
        rectangle.setNorthEast(myPoint);
        assertEquals(myPoint, rectangle.getNorthEast());
    }

    @Test
    void getSouthWest() {
        Rectangle rectangle = new Rectangle(getPointObject(new double[]{1.0, 2.0}), getPointObject(new double[]{1.0, 2.0}));
        PointIn2DimensionalSpace myPoint = getPointObject(new double[]{9.0, 2.0});
        rectangle.setSouthWest(myPoint);
        assertEquals(myPoint, rectangle.getSouthWest());
    }

    @Test
    void getLocationOfPoint() {
        PointIn2DimensionalSpace myPoint = getPointObject(new double[]{10.0, 10.0});
        Rectangle rectangle = new Rectangle(getPointObject(new double[]{5.0, 2.0}), getPointObject(new double[]{1.0, 3.0}));
        assertEquals(Space.Location.LESS_THAN, rectangle.getLocationOfPoint(myPoint, 0));
        assertEquals(Space.Location.LESS_THAN, rectangle.getLocationOfPoint(myPoint, 1));

        rectangle = new Rectangle(getPointObject(new double[]{35.0, 52.0}), getPointObject(new double[]{21.0, 35.0}));
        assertEquals(Space.Location.GREATER_THAN, rectangle.getLocationOfPoint(myPoint, 0));
        assertEquals(Space.Location.GREATER_THAN, rectangle.getLocationOfPoint(myPoint, 1));

        rectangle = new Rectangle(getPointObject(new double[]{15.0, 12.0}), getPointObject(new double[]{1.0, 3.0}));
        assertEquals(Space.Location.CANNOT_SAY, rectangle.getLocationOfPoint(myPoint, 0));
        assertEquals(Space.Location.CANNOT_SAY, rectangle.getLocationOfPoint(myPoint, 1));
    }

    @Test
    void getNumberDimensions() {
        Rectangle rectangle = new Rectangle(getPointObject(new double[]{1.0, 2.0}), getPointObject(new double[]{1.0, 2.0}));
        assertEquals(2, rectangle.getNumberDimensions());
    }

}