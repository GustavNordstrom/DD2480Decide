package com.dd2480.CMV.impl;

import com.dd2480.CMV.Condition;
import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.Parameters;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import com.dd2480.common.CalculationUtils;

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
            double circumRadius = CalculationUtils.calculateCircumcircleRadius(p1, p2, p3);

            // Check if the radius of the circumscribed circle is greater than RADIUS1
            if (CalculationUtils.doubleCompare(circumRadius, radius1) == CalculationUtils.CompType.GT) {
                return true; // There are three points that do not meet the conditions
            }
        }
        return false; // All three conditions are met
    }
}

