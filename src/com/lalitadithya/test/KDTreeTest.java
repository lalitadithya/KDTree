package com.lalitadithya.test;

import com.lalitadithya.KDTree.KDTree;
import com.lalitadithya.PointIn2DimensionalSpace;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Lalit Adithya on 1/23/2017.
 */
class KDTreeTest {

    private PointIn2DimensionalSpace getPointObject(double[] values) {
        PointIn2DimensionalSpace myPoint = new PointIn2DimensionalSpace();
        ArrayList<Double> point = new ArrayList<Double>();
        for (double value : values) {
            point.add(value);
        }
        myPoint.initialize(point);
        return myPoint;
    }

    private KDTree<Object> constructTree() {
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

    @Test
    void searchRange() {
        KDTree<Object> tree = constructTree();
    }

    @Test
    void searchSingle() {
        KDTree<Object> tree = constructTree();

        assertThrows(IllegalArgumentException.class, () -> {
            tree.searchSingle(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            tree.searchSingle(getPointObject(new double[]{3.0}));
        });

        assertEquals("a", tree.searchSingle(getPointObject(new double[]{3.0, 6.0})));

        assertEquals("d", tree.searchSingle(getPointObject(new double[]{6.0, 12.0})));

        assertEquals("g", tree.searchSingle(getPointObject(new double[]{10.0, 19.0})));

        assertEquals(null, tree.searchSingle(getPointObject(new double[]{16.0, 6.0})));

        assertEquals(null, tree.searchSingle(getPointObject(new double[]{3.0, 68.0})));
    }

    @Test
    void insertNode() {
        KDTree<Object> tree = new KDTree<>(2);

        tree.insertNode(null, getPointObject(new double[]{3.0, 6.0}));
        assertEquals(tree.getSize(), 1);
        assertEquals(false, tree.isEmpty());

        tree.insertNode(null, getPointObject(new double[]{17.0, 15.0}));
        assertEquals(tree.getSize(), 2);
        assertEquals(false, tree.isEmpty());

        tree.insertNode(null, getPointObject(new double[]{13.0, 15.0}));
        assertEquals(tree.getSize(), 3);
        assertEquals(false, tree.isEmpty());

        tree.insertNode(null, getPointObject(new double[]{6.0, 12.0}));
        assertEquals(tree.getSize(), 4);
        assertEquals(false, tree.isEmpty());

        tree.insertNode(null, getPointObject(new double[]{9.0, 1.0}));
        assertEquals(tree.getSize(), 5);
        assertEquals(false, tree.isEmpty());

        tree.insertNode(null, getPointObject(new double[]{2.0, 7.0}));
        assertEquals(tree.getSize(), 6);
        assertEquals(false, tree.isEmpty());

        tree.insertNode(null, getPointObject(new double[]{10.0, 19.0}));
        assertEquals(tree.getSize(), 7);
        assertEquals(false, tree.isEmpty());
    }

    @Test
    void isEmpty() {
        KDTree<Object> tree = new KDTree<>(2);
        assertEquals(tree.isEmpty(), true);

        PointIn2DimensionalSpace myPoint = new PointIn2DimensionalSpace();
        ArrayList<Double> point = new ArrayList<Double>();
        point.add(1.0);
        point.add(2.0);
        myPoint.initialize(point);
        tree.insertNode(null, myPoint);
        assertEquals(tree.isEmpty(), false);
    }

}