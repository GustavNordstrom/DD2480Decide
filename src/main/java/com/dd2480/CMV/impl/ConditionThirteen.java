package com.dd2480.CMV.impl;

import com.dd2480.CMV.Condition;
import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.Parameters;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;

// There exists at least one set of three data points, separated by exactly A PTS and B PTS
// consecutive intervening points, respectively, that cannot be contained within or on a circle of
// radius RADIUS1. In addition, there exists at least one set of three data points (which can be
// the same or different from the three data points just mentioned) separated by exactly A PTS
// and B PTS consecutive intervening points, respectively, that can be contained in or on a
// circle of radius RADIUS2. Both parts must be true for the LIC to be true. The condition is
// not met when NUMPOINTS < 5.
// 0 ≤ RADIUS2

public class ConditionThirteen implements Condition{

    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        int aPts = params.getAPTS();
        int bPts = params.getBPTS();
        double radius1 = params.getRADIUS1();
        double radius2 = params.getRADIUS2();
        boolean cond1 = false;
        boolean cond2 = false;

        // Invalid if NUMPOINTS < 5
        if (pointCollection.size() < 5) {
            return false;
        }


        for(int i = 0; i < pointCollection.size() - aPts - bPts - 2; i++) {
            Point p1 = pointCollection.getPoint(i);
            Point p2 = pointCollection.getPoint(i + aPts + 1);
            Point p3 = pointCollection.getPoint(i + aPts + bPts + 2);

            System.out.println(p1);
            System.out.println(p2);
            System.out.println(p3);

            // Calculate the circumcircle radius
            double circumRadius = calculateCircumcircleRadius(p1, p2, p3);

            System.out.println(circumRadius);

            // Points cannot be contained within a circle of RADIUS1
            if (circumRadius > radius1) {
                cond1 = true;
            }

            // Points can be contained within a circle of RADIUS2
            if (circumRadius <= radius2) {
                cond2 = true;
            }

            if (cond1 && cond2) {
                return true;
            }
        }

        System.out.println(cond1);
        System.out.println(cond2);

        return false;
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
