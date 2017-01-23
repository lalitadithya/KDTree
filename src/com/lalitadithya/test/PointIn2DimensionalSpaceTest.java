package com.lalitadithya.test;

import com.lalitadithya.PointIn2DimensionalSpace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Lalit Adithya on 1/23/2017.
 */
class PointIn2DimensionalSpaceTest {
    private PointIn2DimensionalSpace myPoint;

    @BeforeEach
    void SetUp() {
        myPoint = new PointIn2DimensionalSpace();
        ArrayList<Double> point = new ArrayList<>();
        point.add(1.0);
        point.add(2.0);
        myPoint.initialize(point);
    }

    @Test
    void getNthDimension() {
        assertEquals(myPoint.getNthDimension(0), 1.0);
        assertEquals(myPoint.getNthDimension(1), 2.0);
    }

    @Test
    void setNthDimension() {
        myPoint.setNthDimension(1, 5.0);
        assertEquals(myPoint.getNthDimension(1), 5.0);
    }

    @Test
    void getNumberOfDimensions() {
        PointIn2DimensionalSpace myPoint = new PointIn2DimensionalSpace();
        assertEquals(myPoint.getNumberOfDimensions(), 0);

        ArrayList<Double> point = new ArrayList<>();
        point.add(1.0);
        assertThrows(IllegalArgumentException.class, () -> myPoint.initialize(point));

        point.add(2.0);
        myPoint.initialize(point);
        assertEquals(myPoint.getNumberOfDimensions(), 2);
    }

}