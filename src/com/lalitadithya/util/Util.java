package com.lalitadithya.util;

import com.lalitadithya.KDTree.KDTree;
import com.lalitadithya.PointIn2DimensionalSpace;

import java.util.ArrayList;

/**
 * Created by Lalit Adithya on 1/24/2017.
 */
public class Util {

    public static PointIn2DimensionalSpace getPointObject(double[] values) {
        PointIn2DimensionalSpace myPoint = new PointIn2DimensionalSpace();
        ArrayList<Double> point = new ArrayList<Double>();
        for (double value : values) {
            point.add(value);
        }
        myPoint.initialize(point);
        return myPoint;
    }

    public static KDTree<Object> constructTree() {
        KDTree<Object> tree = new KDTree<>(2);
        tree.insertNode("a", getPointObject(new double[]{3.0, 6.0}));
        tree.insertNode("b", getPointObject(new double[]{17.0, 15.0}));
        tree.insertNode("c", getPointObject(new double[]{13.0, 15.0}));
        tree.insertNode("d", getPointObject(new double[]{6.0, 12.0}));
        tree.insertNode("e", getPointObject(new double[]{9.0, 1.0}));
        tree.insertNode("f", getPointObject(new double[]{2.0, 7.0}));
        tree.insertNode("g", getPointObject(new double[]{10.0, 19.0}));
        return tree;
    }
}
