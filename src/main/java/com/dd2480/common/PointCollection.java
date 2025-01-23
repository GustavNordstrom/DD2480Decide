package com.dd2480.common;

import java.util.ArrayList;
import java.util.List;

public class PointCollection {
    private List<Point> points;

    public PointCollection() {
        points = new ArrayList<>(100);  // Create a list to hold 100 points
    }

    public Point getPoint(int index) {
        if (index < 0 || index >= points.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return points.get(index);
    }

    public void addPoint(Point point) {
        if (points.size() < 100) {
            points.add(point);
        } else {
            throw new IllegalStateException("Cannot add more than 100 points");
        }
    }

    public int size() {
        return points.size();
    }
}