package com.dd2480.CMV.impl;

import com.dd2480.CMV.Condition;
import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.Parameters;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;

/*
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
            if (p1.equals(p2) || p3.equals(p2)) {
                continue;
            }

            // Calculate angle
            double angle = calculateAngle(p1, p2, p3);

            // Check if the angle meets the conditions
            if (angle < Math.PI - epsilon || angle > Math.PI + epsilon) {
                return true; // Meet the condition
            }
        }

        return false; // All points do not meet the conditions
    }

    // Calculate the angle with p2 as the vertex
    private double calculateAngle(Point p1, Point p2, Point p3) {
        // Vector v1 and v2
        double v1x = p1.getX() - p2.getX();
        double v1y = p1.getY() - p2.getY();
        double v2x = p3.getX() - p2.getX();
        double v2y = p3.getY() - p2.getY();

        // Calculate the dot product and magnitude of vectors
        double dotProduct = (v1x * v2x) + (v1y * v2y);
        double magnitudeV1 = Math.sqrt(v1x * v1x + v1y * v1y);
        double magnitudeV2 = Math.sqrt(v2x * v2x + v2y * v2y);

        // Invalid if the modulus is 0
        if (magnitudeV1 == 0 || magnitudeV2 == 0) {
            return Double.NaN;
        }

        // Calculates the cosine of the angle
        double cosAngle = dotProduct / (magnitudeV1 * magnitudeV2);

        // Ensures that the cosine is in the range [-1, 1]
        cosAngle = Math.max(-1.0, Math.min(1.0, cosAngle));

        // System.out.println(cosAngle);

        return Math.acos(cosAngle);
    }
}
