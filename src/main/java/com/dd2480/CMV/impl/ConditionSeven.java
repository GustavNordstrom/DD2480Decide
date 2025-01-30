package com.dd2480.CMV.impl;

import com.dd2480.CMV.ConditionContext;
import com.dd2480.CMV.Condition;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import com.dd2480.common.CalculationUtils;
import com.dd2480.common.Parameters;

/*
 * There exists at least one set of two data points separated by exactly K_PTS 
 * consecutive intervening points that are a distance greater than the length, 
 * LENGTH1, apart. The condition is not met when NUMPOINTS < 3.
 * 1 ≤ K_PTS ≤ (NUMPOINTS−2)
 */
public class ConditionSeven implements Condition {
    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        int kPts = params.getKPTS();
        double length1 = params.getLENGTH1();

        // The condition is not met when NUMPOINTS < 3 or K_PTS < 1 or K_PTS >
        // (NUMPOINTS - 2)
        if (pointCollection.size() < 3 || kPts < 1 || kPts > (pointCollection.size() - 2)) {
            return false;
        }

        // Traverse all possible point pairs
        for (int i = 0; i < pointCollection.size() - kPts - 1; ++i) {
            Point p1 = pointCollection.getPoint(i); // Start
            Point p2 = pointCollection.getPoint(i + kPts + 1); // The point after K points

            // Calculate the distance between points
            double distance = euclideanDistance(p1, p2);

            // Meet the condition if distance > length1
            if (CalculationUtils.doubleCompare(distance, length1) == CalculationUtils.CompType.GT) {
                return true;
            }
        }

        return false;
    }

    // Calculate the distance between points
    private double euclideanDistance(Point p1, Point p2) {
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
