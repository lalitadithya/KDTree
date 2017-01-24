package com.lalitadithya.util;

import com.lalitadithya.KDTree.KDTree;
import com.lalitadithya.PointIn2DimensionalSpace;

import java.util.ArrayList;
import java.util.List;

/**
 * An utility class used for testing
 */
public final class Util {

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

    public static KDTree<Object> constructTree1() {
        KDTree<Object> tree = new KDTree<>(2);
        tree.insertNode("a", getPointObject(new double[]{30, 40}));
        tree.insertNode("b", getPointObject(new double[]{5, 25}));
        tree.insertNode("c", getPointObject(new double[]{70, 70}));
        tree.insertNode("d", getPointObject(new double[]{10, 12}));
        tree.insertNode("e", getPointObject(new double[]{50, 30}));
        tree.insertNode("f", getPointObject(new double[]{35, 45}));
        return tree;
    }

    public static boolean checkEqality(List<Object> actual, Object[] expected) {
        for (Object exp : expected) {
            if (actual.indexOf(exp) == -1) {
                return false;
            }
        }
        return true;
    }
}
