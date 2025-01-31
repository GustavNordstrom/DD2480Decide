package com.dd2480.CMV.impl;

import com.dd2480.CMV.ConditionContext;
import com.dd2480.CMV.Condition;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import com.dd2480.common.Parameters;

/**
 * LIC 11:
 * There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
 * exactly G_PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j ) 
 * The condition is not met when NUMPOINTS < 3.
 * 1 <= G_PTS <= NUMPOINTS−2
 */
public class ConditionEleven implements Condition {

    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        int gPts = params.getGPTS();

        // Invalid if NUMPOINTS < 3 or G_PTS is out of range
        if (pointCollection.size() < 3 || gPts < 1 || gPts > pointCollection.size() - 2) {
            return false;
        }

        // Traverse all possible pairs of points
        for (int i = 0; i < pointCollection.size() - gPts - 1; ++i) {
            Point p1 = pointCollection.getPoint(i); // First point
            Point p2 = pointCollection.getPoint(i + gPts + 1); // Second point, separated by G_PTS

            // Check if X[j] - X[i] < 0
            if (p2.getX() - p1.getX() < 0) {
                return true;
            }
        }

        return false;
    }
}