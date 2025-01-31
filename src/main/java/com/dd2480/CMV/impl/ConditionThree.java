package com.dd2480.CMV.impl;

import com.dd2480.CMV.Condition;
import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import com.dd2480.common.CalculationUtils;
import com.dd2480.common.Parameters;

/**
 * LIC 3:
 * There exists at least one set of three consecutive data points that are the vertices of a triangle
 * with area greater than AREA1.
 */
public class ConditionThree implements Condition {
    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        double area1 = params.getAREA1();

        // Invalid if less than points
        if (pointCollection.size() < 3) {
            return false;
        }

        // Traverse all three consecutive points
        for (int i = 1; i < pointCollection.size() - 1; ++i) {
            Point p1 = pointCollection.getPoint(i - 1);
            Point p2 = pointCollection.getPoint(i);
            Point p3 = pointCollection.getPoint(i + 1);

            // Calculate the triangle area
            double area = CalculationUtils.calculateTriangleArea(p1, p2, p3);

            // Meet the condition if area > AREA1
            if (CalculationUtils.doubleCompare(area, area1) == CalculationUtils.CompType.GT) {
                return true;
            }
        }

        return false;
    }
}
