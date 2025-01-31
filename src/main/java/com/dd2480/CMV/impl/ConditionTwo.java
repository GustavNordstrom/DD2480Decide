package com.dd2480.CMV.impl;

import com.dd2480.CMV.Condition;
import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.CalculationUtils;
import com.dd2480.common.Parameters;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;

/**
 * LIC 2:
 * There exists at least one set of three consecutive data points which form an angle such that:
 * angle < (PI−EPSILON)
 * or
 * angle > (PI+EPSILON)
 * The second of the three consecutive points is always the vertex of the angle. If either the first
 * point or the last point (or both) coincides with the vertex, the angle is undefined and the LIC
 * is not satisfied by those three points.
 * (0 ≤ EPSILON < PI)
 */
public class ConditionTwo implements Condition {
    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        double epsilon = params.getEPSILON();

        // Traverse all three consecutive points
        for (int i = 1; i < pointCollection.size() - 1; ++i) {
            Point p1 = pointCollection.getPoint(i - 1);
            Point p2 = pointCollection.getPoint(i); // vertex
            Point p3 = pointCollection.getPoint(i + 1);

            // Invalid If either the first point or the last point (or both) coincides with
            // the vertex
            if (CalculationUtils.doubleCompare(Point.distanceOf(p1, p2), 0) == CalculationUtils.CompType.EQ || 
            CalculationUtils.doubleCompare(Point.distanceOf(p3, p2), 0) == CalculationUtils.CompType.EQ) {
                continue;
            }

            // Calculate angle
            double angle = CalculationUtils.calculateAngle(p1, p2, p3);

            // Check if the angle meets the conditions
            
            if (CalculationUtils.doubleCompare(angle, Math.PI - epsilon) == CalculationUtils.CompType.LT || CalculationUtils.doubleCompare(angle, Math.PI + epsilon) == CalculationUtils.CompType.GT) {
                return true; // Meet the condition
            }
        }

        return false; // All points do not meet the conditions
    }
}
