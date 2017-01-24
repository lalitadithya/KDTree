package com.lalitadithya.test;

import com.lalitadithya.KDTree.KDTree;
import com.lalitadithya.PointIn2DimensionalSpace;
import com.lalitadithya.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.lalitadithya.util.Util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Lalit Adithya on 1/23/2017.
 */
class KDTreeTest {
    @Test
    void delete() {
        KDTree<Object> tree = constructTree1();
        tree.delete(getPointObject(new double[]{30.0, 40.0}));
        assertEquals(null, tree.searchSingle(getPointObject(new double[]{30.0, 40.0})));

        tree.delete(getPointObject(new double[]{70.0, 70.0}));
        assertEquals(null, tree.searchSingle(getPointObject(new double[]{70.0, 70.0})));
    }

    @Test
    void findMinimum() {
        KDTree<Object> tree = constructTree();
        assertEquals(2, tree.findMinimum(0));
        assertEquals(1, tree.findMinimum(1));
    }

    @Test
    void searchRange() {
        KDTree<Object> tree = constructTree();

        assertThrows(NullPointerException.class, () -> {
            tree.searchRange(null);
        });

        Rectangle space = new Rectangle(getPointObject(new double[]{18, 18}), getPointObject(new double[]{12, 14}));
        List<Object> result = tree.searchRange(space);
        assertEquals(true, checkEqality(result, new String[]{"b", "c"}));

        space = new Rectangle(getPointObject(new double[]{20, 20}), getPointObject(new double[]{0, 0}));
        result = tree.searchRange(space);
        assertEquals(true, checkEqality(result, new String[]{"a", "b", "c", "d", "e", "f", "g"}));

        space = new Rectangle(getPointObject(new double[]{40, 40}), getPointObject(new double[]{30, 30}));
        result = tree.searchRange(space);
        assertEquals(0, result.size());
    }

    @Test
    void searchSingle() {
        KDTree<Object> tree = constructTree();

        assertThrows(NullPointerException.class, () -> {
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