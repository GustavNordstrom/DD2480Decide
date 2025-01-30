package com.dd2480.CMV.impl;

import com.dd2480.CMV.ConditionContext;
import com.dd2480.CMV.Condition;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import com.dd2480.common.CalculationUtils;
import com.dd2480.common.Parameters;

/*
 * There exists at least one set of three data points separated by exactly E_PTS and F_PTS 
 * consecutive intervening points, respectively, that are the vertices of a triangle with 
 * area greater than AREA1. The condition is not met when NUMPOINTS < 5. 
 * 1 <= E PTS, 1 <= F PTS
 * E_PTS+F_PTS <= NUMPOINTS−3
 */
public class ConditionTen implements Condition {
    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        int ePts = params.getEPTS();
        int fPts = params.getFPTS();
        double area1 = params.getAREA1();

        // Invalid if NUMPOINTS < 5 or EPTS + FPTS out of range
        if (pointCollection.size() < 5 || ePts < 1 || fPts < 1 || ePts + fPts > pointCollection.size() - 3) {
            return false;
        }

        // Traverse all possible sets of three points
        for (int i = 0; i < pointCollection.size() - ePts - fPts - 2; ++i) {
            Point p1 = pointCollection.getPoint(i); // First point
            Point p2 = pointCollection.getPoint(i + ePts + 1); // Second point (vertex)
            Point p3 = pointCollection.getPoint(i + ePts + fPts + 2); // Third point

            // Calculate the area of the triangle formed by the three points
            double area = CalculationUtils.calculateTriangleArea(p1, p2, p3);

            // Check if the area exceeds AREA1
            if (CalculationUtils.doubleCompare(area, area1) == CalculationUtils.CompType.GT) {
                return true;
            }
        }

        return false;
    }
}
