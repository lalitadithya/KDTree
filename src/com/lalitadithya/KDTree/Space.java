package com.lalitadithya.KDTree;

/**
 * Created by Lalit Adithya on 1/23/2017.
 */
public interface Space {
    Location getLocationOfPoint(Point point);

    ;

    enum Location {INSIDE, LEFT, RIGHT}
}
