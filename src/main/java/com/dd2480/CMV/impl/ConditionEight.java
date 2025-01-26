package com.dd2480.CMV.impl;

import com.dd2480.CMV.ConditionContext;
import com.dd2480.CMV.Condition;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import com.dd2480.common.Parameters;

/*
 * There exists at least one set of three data points separated by exactly A_PTS and B_PTS
 * consecutive intervening points, respectively, that cannot be contained within or on a circle of
 * radius RADIUS1. The condition is not met when NUMPOINTS < 5.
 * 1 ≤ A_PTS, 1 ≤ B_PTS
 * A_PTS+B_PTS ≤ (NUMPOINTS−3)
 */
public class ConditionEight implements Condition {
    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        int aPts = params.getAPTS();
        int bPts = params.getBPTS();
        double radius1 = params.getRADIUS1();

        // Invalid if NUMPOINTS < 5 or A_PYS < 1 or B_PTS < 1
        // or A_PTS + B_PTS > (NUMPOINTS - 3)
        if (pointCollection.size() < 5 || aPts < 1 || bPts < 1 || aPts + bPts > pointCollection.size() - 3) {
            return false;
        }

        // Traverse all possible point pairs
        for (int i = 0; i < pointCollection.size() - aPts - bPts - 2; ++i) {
            Point p1 = pointCollection.getPoint(i); // Start
            Point p2 = pointCollection.getPoint(i + aPts + 1); // Interval A_PTS
            Point p3 = pointCollection.getPoint(i + aPts + bPts + 2); // Interval B_PTS

            // Calculate whether three points can be contained in the circle
            double circumRadius = calculateCircumcircleRadius(p1, p2, p3);
            System.out.println("Circumcircle Radius: " + circumRadius + ", RADIUS1: " + radius1);

            // Meet the condition if circumcircle radius > RADIUS1
            if (circumRadius > radius1) {
                return true;
            }
        }

        return false;
    }

    // Calculate the circumcircle radius
    private double calculateCircumcircleRadius(Point p1, Point p2, Point p3) {
        double a = Point.distanceOf(p1, p2);
        double b = Point.distanceOf(p2, p3);
        double c = Point.distanceOf(p1, p3);

        // If the points are collinear
        if (areCollinear(p1, p2, p3)) {
            // The circumcircle radius equals to half of the longest edge.
            return Math.max(a, Math.max(b, c)) / 2.0;
        }

        // Calculate the area of the triangle
        double s = (a + b + c) / 2.0; // Half circumference
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c)); // Heron's formula

        // r = (a * b * c) / (4 * area)
        return (a * b * c) / (4.0 * area);
    }

    // Determine whether the points are collinear or not
    private boolean areCollinear(Point p1, Point p2, Point p3) {
        // By area, collinear if the area is zero
        return Math.abs(
                (p1.getX() * (p2.getY() - p3.getY()) +
                        p2.getX() * (p3.getY() - p1.getY()) +
                        p3.getX() * (p1.getY() - p2.getY())) / 2.0) < 1e-6; // threshold
    }
}
