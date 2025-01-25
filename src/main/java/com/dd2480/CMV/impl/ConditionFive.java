package com.dd2480.CMV.impl;

import com.dd2480.CMV.Condition;
import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;

/*
 * There exists at least one set of two consecutive data points, (X[i],Y[i]) and (X[j],Y[j]), such
 * that X[j] - X[i] < 0. (where i = j-1)
 */
public class ConditionFive implements Condition {
    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();

        // If the number of points is less than 2, no consecutive pairs can be formed
        if (pointCollection.size() < 2) {
            return false;
        }

        // Traverse all consecutive pairs of points
        for (int i = 0; i < pointCollection.size() - 1; ++i) {
            Point p1 = pointCollection.getPoint(i);
            Point p2 = pointCollection.getPoint(i + 1);

            // X[j] - X[i] < 0
            if (p2.getX() - p1.getX() < 0) {
                return true;
            }
        }

        return false;
    }
}
