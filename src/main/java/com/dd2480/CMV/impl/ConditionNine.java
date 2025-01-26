package com.dd2480.CMV.impl;

import com.dd2480.CMV.ConditionContext;
import com.dd2480.CMV.Condition;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import com.dd2480.common.Parameters;

/*
 * There exists at least one set of three data points separated by exactly C_PTS and D_PTS
 * consecutive intervening points, respectively, that form an angle such that:
 * angle < (PI−EPSILON)
 * or
 * angle > (PI+EPSILON)
 * The second point of the set of three points is always the vertex of the angle. If either the first
 * point or the last point (or both) coincide with the vertex, the angle is undefined and the LIC
 * is not satisfied by those three points. When NUMPOINTS < 5, the condition is not met.
 * 1 <= C PTS, 1 <= D PTS
 * C_PTS+D_PTS <= NUMPOINTS−3
 */
public class ConditionNine implements Condition {

    @Override
    public boolean evaluate(ConditionContext conditionContext) {
        PointCollection pointCollection = conditionContext.getPointCollection();
        Parameters params = conditionContext.getParameters();
        int cPts = params.getCPTS(); // Number of points between the first and the vertex
        int dPts = params.getDPTS(); // Number of points between the vertex and the last
        double epsilon = params.getEPSILON(); // Angular deviation allowance

        // Condition is not met if NUMPOINTS < 5
        // or C_PTS < 1 or D_PTS < 1
        // or C_PTS+D_PTS <= NUMPOINTS−3
        if (pointCollection.size() < 5 || cPts < 1 || dPts < 1 || cPts + dPts > pointCollection.size() - 3) {
            return false;
        }

        // Traverse all possible sets of three points
        for (int i = 0; i < pointCollection.size() - cPts - dPts - 2; ++i) {
            Point p1 = pointCollection.getPoint(i); // The 1st point
            Point p2 = pointCollection.getPoint(i + cPts + 1); // Vertex, the 2nd point
            Point p3 = pointCollection.getPoint(i + cPts + dPts + 2); // The 3rd point

            // Skip sets where the vertex coincides with the first or last point
            if (p1.equals(p2) || p3.equals(p2)) {
                continue;
            }

            // Calculate the angle formed at the vertex
            double angle = calculateAngle(p1, p2, p3);

            // Check if the angle meets the condition
            if (angle < Math.PI - epsilon || angle > Math.PI + epsilon) {
                return true;
            }
        }

        return false;
    }

    // Calculates the angle formed by three points (p1, p2, p3) where p2 is the
    // vertex.
    private double calculateAngle(Point p1, Point p2, Point p3) {
        // Vectors from the vertex to the other two points
        double v1x = p1.getX() - p2.getX();
        double v1y = p1.getY() - p2.getY();
        double v2x = p3.getX() - p2.getX();
        double v2y = p3.getY() - p2.getY();

        // Calculate the dot product and magnitudes of the vectors
        double dotProduct = (v1x * v2x) + (v1y * v2y);
        double magnitudeV1 = Math.sqrt(v1x * v1x + v1y * v1y);
        double magnitudeV2 = Math.sqrt(v2x * v2x + v2y * v2y);

        // If either vector has zero length, the angle is undefined
        if (magnitudeV1 == 0 || magnitudeV2 == 0) {
            return Double.NaN;
        }

        // Calculate the cosine of the angle
        double cosAngle = dotProduct / (magnitudeV1 * magnitudeV2);

        // Ensure the cosine value is within the valid range [-1, 1]
        cosAngle = Math.max(-1.0, Math.min(1.0, cosAngle));

        return Math.acos(cosAngle);
    }
}
