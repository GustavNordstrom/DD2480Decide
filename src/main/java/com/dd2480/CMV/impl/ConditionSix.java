package com.dd2480.CMV.impl;

import com.dd2480.CMV.ConditionContext;
import com.dd2480.CMV.Condition;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import com.dd2480.common.CalculationUtils;
import com.dd2480.common.Parameters;

/**
 * LIC 6: 
 * There exists at least one set of N_PTS consecutive data points such that at least one of the
 * points lies a distance greater than DIST from the line joining the first and last of these N PTS
 * points. If the first and last points of these N PTS are identical, then the calculated distance
 * to compare with DIST will be the distance from the coincident point to all other points of
 * the N_PTS consecutive points. The condition is not met when NUMPOINTS < 3.
 * (3 ≤ N_PTS ≤ NUMPOINTS), (0 ≤ DIST)
 */
public class ConditionSix implements Condition {
    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        int nPts = params.getNPTS();
        double dist = params.getDIST();

        // Invalid if the number of points less than 3 or N_PTS
        if (pointCollection.size() < 3 || nPts > pointCollection.size()) {
            return false;
        }

        // Iterate over all N_PTS subsets
        for (int i = 0; i <= pointCollection.size() - nPts; ++i) {
            Point p1 = pointCollection.getPoint(i); // The first point of the subset
            Point pn = pointCollection.getPoint(i + nPts - 1); // The last point of the subset

            // Traverse the middle points and calculate the distance
            for (int j = i + 1; j < i + nPts - 1; ++j) {
                Point pj = pointCollection.getPoint(j);
                double distance;

                if (p1.equals(pn)) {
                    // If the first and last points coincide, use the Euclidean distance
                    distance = euclideanDistance(pj, p1);
                } else {
                    // Calculate the perpendicular distance from a point to a line
                    distance = pointToLineDistance(pj, p1, pn);
                }

                // Meet the condition if the distance is greater than DIST
                if (CalculationUtils.doubleCompare(distance, dist) == CalculationUtils.CompType.GT) {
                    return true;
                }
            }
        }

        return false;
    }

    // Calculate the perpendicular distance from a point to a line
    private double pointToLineDistance(Point p, Point p1, Point p2) {
        double x1 = p1.getX(), y1 = p1.getY();
        double x2 = p2.getX(), y2 = p2.getY();
        double x = p.getX(), y = p.getY();

        double numerator = Math.abs((y2 - y1) * x - (x2 - x1) * y + x2 * y1 - y2 * x1);
        double denominator = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));

        return CalculationUtils.doubleCompare(denominator, 0) == CalculationUtils.CompType.EQ ? 0 : numerator / denominator;
    }

    // Calculate the Euclidean distance between two points
    private double euclideanDistance(Point p1, Point p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
