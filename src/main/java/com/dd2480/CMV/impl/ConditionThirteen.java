package com.dd2480.CMV.impl;

import com.dd2480.CMV.Condition;
import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.Parameters;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import com.dd2480.common.CalculationUtils;

// There exists at least one set of three data points, separated by exactly A PTS and B PTS
// consecutive intervening points, respectively, that cannot be contained within or on a circle of
// radius RADIUS1. In addition, there exists at least one set of three data points (which can be
// the same or different from the three data points just mentioned) separated by exactly A PTS
// and B PTS consecutive intervening points, respectively, that can be contained in or on a
// circle of radius RADIUS2. Both parts must be true for the LIC to be true. The condition is
// not met when NUMPOINTS < 5.
// 0 ≤ RADIUS2

public class ConditionThirteen implements Condition{

    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        int aPts = params.getAPTS();
        int bPts = params.getBPTS();
        double radius1 = params.getRADIUS1();
        double radius2 = params.getRADIUS2();
        boolean cond1 = false;
        boolean cond2 = false;

        // Invalid if NUMPOINTS < 5
        if (pointCollection.size() < 5 || radius2 <= 0) {
            return false;
        }


        for(int i = 0; i < pointCollection.size() - aPts - bPts - 2; i++) {
            Point p1 = pointCollection.getPoint(i);
            Point p2 = pointCollection.getPoint(i + aPts + 1);
            Point p3 = pointCollection.getPoint(i + aPts + bPts + 2);

            // Calculate the circumcircle radius
            double circumRadius = CalculationUtils.calculateCircumcircleRadius(p1, p2, p3);

            // Points cannot be contained within a circle of RADIUS1
            if (circumRadius > radius1) {
                cond1 = true;
            }

            // Points can be contained within a circle of RADIUS2
            if (circumRadius <= radius2) {
                cond2 = true;
            }

            if (cond1 && cond2) {
                return true;
            }
        }

        return false;
    }
}
