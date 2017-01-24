package com.lalitadithya.test;

import com.lalitadithya.KDTree.ISpace;
import com.lalitadithya.PointIn2DimensionalSpace;
import com.lalitadithya.Rectangle;
import org.junit.jupiter.api.Test;

import static com.lalitadithya.util.Util.getPointObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Lalit Adithya on 1/24/2017.
 */
class RectangleTest {
    @Test
    void isInside() {
        Rectangle rectangle = new Rectangle(getPointObject(new double[]{1.0, 2.0}), getPointObject(new double[]{1.0, 2.0}));
        assertThrows(NullPointerException.class, () -> {
            rectangle.isInside(null);
        });

        PointIn2DimensionalSpace myPoint = getPointObject(new double[]{10.0, 10.0});
        Rectangle rectangle1 = new Rectangle(getPointObject(new double[]{5.0, 5.0}), getPointObject(new double[]{2.0, 3.0}));
        assertEquals(false, rectangle1.isInside(myPoint));

        myPoint = getPointObject(new double[]{2.0, 4.0});
        assertEquals(true, rectangle1.isInside(myPoint));
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
        assertEquals(ISpace.Location.WEST, rectangle.getLocationOfPoint(myPoint, 0));
        assertEquals(ISpace.Location.WEST, rectangle.getLocationOfPoint(myPoint, 1));

        rectangle = new Rectangle(getPointObject(new double[]{35.0, 52.0}), getPointObject(new double[]{21.0, 35.0}));
        assertEquals(ISpace.Location.EAST, rectangle.getLocationOfPoint(myPoint, 0));
        assertEquals(ISpace.Location.EAST, rectangle.getLocationOfPoint(myPoint, 1));

        rectangle = new Rectangle(getPointObject(new double[]{15.0, 12.0}), getPointObject(new double[]{1.0, 3.0}));
        assertEquals(ISpace.Location.CANNOT_SAY, rectangle.getLocationOfPoint(myPoint, 0));
        assertEquals(ISpace.Location.CANNOT_SAY, rectangle.getLocationOfPoint(myPoint, 1));
    }

    @Test
    void getNumberDimensions() {
        Rectangle rectangle = new Rectangle(getPointObject(new double[]{1.0, 2.0}), getPointObject(new double[]{1.0, 2.0}));
        assertEquals(2, rectangle.getNumberDimensions());
    }

}