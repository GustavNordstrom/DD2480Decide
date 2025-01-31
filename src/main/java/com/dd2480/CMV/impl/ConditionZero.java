package com.dd2480.CMV.impl;

import com.dd2480.CMV.Condition;
import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.CalculationUtils;
import com.dd2480.common.Parameters;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;


/**
 * Class for evaluating LIC 0, which reads:
 * There exists at least one set of two consecutive data points that are a distance greater than
 * the length, LENGTH1, apart.
 */
public class ConditionZero implements Condition {
    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        for (int i = 0; i < pointCollection.size() - 1; ++i) {
            Point p1 = pointCollection.getPoint(i);
            Point p2 = pointCollection.getPoint(i + 1);
            double dist = Point.distanceOf(p1, p2);
            if (CalculationUtils.doubleCompare(dist, params.getLENGTH1()) == CalculationUtils.CompType.GT) {
                return true;
            }

        }
;
        return false;
    }
}
