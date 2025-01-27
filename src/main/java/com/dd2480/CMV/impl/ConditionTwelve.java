package com.dd2480.CMV.impl;

import com.dd2480.CMV.ConditionContext;
import com.dd2480.CMV.Condition;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import com.dd2480.common.Parameters;

/*
 * There exists at least one set of two data points, separated by exactly K PTS consecutive
 * intervening points, which are a distance greater than the length, LENGTH1, apart. 
 * In addition, there exists at least one set of two data points (which can be the same 
 * or different from the two data points just mentioned), separated by exactly K_PTS 
 * consecutive intervening points, that are a distance less than the length, LENGTH2, apart. 
 * Both parts must be true for the LIC to be true. The condition is not met when NUMPOINTS < 3.
 * 0 <= LENGTH2
 */
public class ConditionTwelve implements Condition {
    /*
     * Condition A: There exists at least one pair of points separated by exactly
     * KPTS
     * such that the distance between the points is greater than LENGTH1.
     * 
     * Condition B: There exists at least one pair of points (possibly the same or
     * different from Condition A) separated by exactly KPTS such that the distance
     * between the points is less than LENGTH2.
     */
    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        int kPts = params.getKPTS();
        double length1 = params.getLENGTH1(); // Distance threshold for Condition A
        double length2 = params.getLENGTH2(); // Distance threshold for Condition B

        // Invalid if NUMPOINTS < 3 or KPTS is out of range
        if (pointCollection.size() < 3 || kPts < 0 || kPts > pointCollection.size() - 2 || length2 < 0) {
            return false;
        }

        boolean conditionA = false; // Distance > LENGTH1
        boolean conditionB = false; // Distance < LENGTH2

        // Traverse all valid pairs of points
        for (int i = 0; i < pointCollection.size() - kPts - 1; ++i) {
            Point p1 = pointCollection.getPoint(i); // The 1st point
            Point p2 = pointCollection.getPoint(i + kPts + 1); // The 2nd point

            // Calculate the distance between the points
            double distance = euclideanDistance(p1, p2);

            // Check Condition A
            if (distance > length1) {
                conditionA = true;
            }

            // Check Condition B
            if (distance < length2) {
                conditionB = true;
            }

            // If both conditions are satisfied, return true
            if (conditionA && conditionB) {
                return true;
            }
        }

        // If either condition is not satisfied, return false
        return false;
    }

    // Calculates the Euclidean distance between two points.
    private double euclideanDistance(Point p1, Point p2) {
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
