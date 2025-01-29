package com.dd2480.CMV.impl;

import com.dd2480.CMV.ConditionContext;
import com.dd2480.CMV.Condition;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import com.dd2480.common.CalculationUtils;
import com.dd2480.common.Parameters;

/*
 * Class that handles the fourteenth CMV condition
 * 
 * There exists at least one set of three data points, separated by exactly E PTS and F PTS consecutive 
 * intervening points, respectively, that are the vertices of a triangle with area greater than AREA1. 
 * In addition, there exist three data points (which can be the same or different from the three data points 
 * just mentioned) separated by exactly E PTS and F PTS consecutive intervening points, respectively, that 
 * are the vertices of a triangle with area less than AREA2. Both parts must be true for the LIC to be true. 
 * The condition is not met when NUMPOINTS < 5.
 * 0 ≤ AREA2
 */
public class ConditionFourteen implements Condition {
    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        int ePts = params.getEPTS();
        int fPts = params.getFPTS();
        double area1 = params.getAREA1();
        double area2 = params.getAREA2();

        // Condition not met when NUMPOINTS < 5 or 0 ≤ AREA2. Also constraints from lic 10
        if (pointCollection.size() < 5 || ePts < 1 || fPts < 1 || ePts + fPts > pointCollection.size() - 3 || area2 < 0 ) {
            return false;
        }

        boolean triangleGreaterThanAREA1Exists = false;
        boolean triangleSmallerThanAREA2Exists = false;

        // Traverse possible points
        for (int i = 0; i < pointCollection.size() - ePts - fPts - 2; ++i) {
            Point p1 = pointCollection.getPoint(i); 
            Point p2 = pointCollection.getPoint(i + ePts + 1); 
            Point p3 = pointCollection.getPoint(i + ePts + fPts + 2); 

            double area = CalculationUtils.calculateTriangleArea(p1, p2, p3);

            // Check for both conditions
            if (area > area1) {
                triangleGreaterThanAREA1Exists = true;
            }
            if (area < area2) {
                triangleSmallerThanAREA2Exists = true;
            }

            // If both conditions are satisfied then return true
            if (triangleGreaterThanAREA1Exists && triangleSmallerThanAREA2Exists) {
                return true;
            }
        }

        return false;
    }
}
