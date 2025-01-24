package com.dd2480.CMV.impl;

import com.dd2480.CMV.Condition;
import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.Parameters;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;

/*
 * There exists at least one set of three consecutive data points 
 * that cannot all be contained within or on a circle of radius RADIUS1.
 */

public class ConditionOne implements Condition {
    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        double radius1 = params.getRADIUS1();

        // Traverse all three consecutive points
        for (int i = 0; i < pointCollection.size() - 2; ++i) {
            Point p1 = pointCollection.getPoint(i);
            Point p2 = pointCollection.getPoint(i + 1);
            Point p3 = pointCollection.getPoint(i + 2);

            // Calculate the circumcircle radius
            double circumRadius = calculateCircumcircleRadius(p1, p2, p3);

            // Check if the radius of the circumscribed circle is greater than RADIUS1
            if (circumRadius > radius1) {
                return true; // There are three points that do not meet the conditions
            }
        }
        return false; // All three conditions are met
    }

    // Calculate the circumcircle radius
    private double calculateCircumcircleRadius(Point p1, Point p2, Point p3) {
        // All three points are collinear
        if (areCollinear(p1, p2, p3)) {
            double d1 = Point.distanceOf(p1, p2);
            double d2 = Point.distanceOf(p2, p3);
            double d3 = Point.distanceOf(p1, p3);
            return Math.max(d1, Math.max(d2, d3)) / 2.0;
        }

        // Calculate the radius of the circumscribed circle when three points are not
        // collinear
        double a = Point.distanceOf(p1, p2);
        double b = Point.distanceOf(p2, p3);
        double c = Point.distanceOf(p1, p3);

        double semiPerimeter = (a + b + c) / 2.0;
        double triangleArea = Math
                .sqrt(semiPerimeter * (semiPerimeter - a) * (semiPerimeter - b) * (semiPerimeter - c));

        // The formula for the radius of the circumscribed circle is: R = (a * b * c) /
        // (4 * triangleArea)
        return (a * b * c) / (4.0 * triangleArea);
    }

    // Determine whether three points are collinear
    private boolean areCollinear(Point p1, Point p2, Point p3) {
        // If the area is 0, then the three points are collinear
        return Math.abs((p1.getX() * (p2.getY() - p3.getY()) +
                p2.getX() * (p3.getY() - p1.getY()) +
                p3.getX() * (p1.getY() - p2.getY())) / 2.0) < 1e-6;
    }
}

