package com.dd2480.CMV.impl;

import com.dd2480.CMV.Condition;
import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import com.dd2480.common.CalculationUtils;
import com.dd2480.common.CalculationUtils.CompType;
import com.dd2480.common.Parameters;

import java.util.HashSet;
import java.util.Set;

/*
 * There exists at least one set of Q_PTS consecutive data points that lie in more than QUADS
 * quadrants. Where there is ambiguity as to which quadrant contains a given point, priority
 * of decision will be by quadrant number, i.e., I, II, III, IV. For example, the data point (0,0)
 * is in quadrant I, the point (-l,0) is in quadrant II, the point (0,-l) is in quadrant III, the point
 * (0,1) is in quadrant I and the point (1,0) is in quadrant I.
 * (2 ≤ Q PTS ≤ NUMPOINTS), (1 ≤ QUADS ≤ 3)
 */
public class ConditionFour implements Condition {
    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        int qPts = params.getQPTS();
        int quads = params.getQUADS();

        // Invalid if the number of points less than Q_PTS
        if (pointCollection.size() < qPts) {
            return false;
        }

        // Traverse all consecutive Q_PTS points
        for (int i = 0; i <= pointCollection.size() - qPts; ++i) {
            Set<Integer> quadrants = new HashSet<>();

            // Check the quadrant of the current Q_PTS points
            for (int j = 0; j < qPts; ++j) {
                Point point = pointCollection.getPoint(i + j);
                int quadrant = getQuadrant(point);
                quadrants.add(quadrant);
            }

            // Meet the condition if the number of quadrants is greater than QUADS
            if (quadrants.size() > quads) {
                return true;
            }
        }

        return false;
    }

    // Determine the quadrant of a point according to its coordinates
    private int getQuadrant(Point point) {
        double x = point.getX();
        double y = point.getY();

        if (CalculationUtils.doubleCompare(x, 0) == CompType.GT &&
        CalculationUtils.doubleCompare(y, 0) == CompType.GT) {
        return 1; // 1st Quadrant
        } else if (CalculationUtils.doubleCompare(x, 0) == CompType.LT &&
                CalculationUtils.doubleCompare(y, 0) == CompType.GT) {
            return 2; // 2nd Quadrant
        } else if (CalculationUtils.doubleCompare(x, 0) == CompType.LT &&
                CalculationUtils.doubleCompare(y, 0) == CompType.LT) {
            return 3; // 3rd Quadrant
        } else if (CalculationUtils.doubleCompare(x, 0) == CompType.GT &&
                CalculationUtils.doubleCompare(y, 0) == CompType.LT) {
            return 4; // 4th Quadrant
        } else if (CalculationUtils.doubleCompare(x, 0) == CompType.EQ &&
                CalculationUtils.doubleCompare(y, 0) == CompType.GT) {
            return 1; // Borderline cases are classified as the 1st quadrant
        } else if (CalculationUtils.doubleCompare(x, 0) == CompType.LT &&
                CalculationUtils.doubleCompare(y, 0) == CompType.EQ) {
            return 2; // Borderline cases are classified as the 2nd quadrant
        } else if (CalculationUtils.doubleCompare(x, 0) == CompType.EQ &&
                CalculationUtils.doubleCompare(y, 0) == CompType.LT) {
            return 3; // Borderline cases are classified as the 3rd quadrant
        } else if (CalculationUtils.doubleCompare(x, 0) == CompType.GT &&
                CalculationUtils.doubleCompare(y, 0) == CompType.EQ) {
            return 1; // Borderline cases are classified as the 1st quadrant
        } else {
            return 1; // (0, 0) is classified as the 1st quadrant
        }
    }
}
