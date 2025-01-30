package com.dd2480.common;

public class CalculationUtils {

    // Double compare as specified in lab assignment
    public enum CompType { LT, EQ, GT }
    
    public static CompType doubleCompare(double A, double B) {
        final double EPSILON = 0.000001; 

        if (Math.abs(A - B) < EPSILON) {
            return CompType.EQ;  
        }
        return (A < B) ? CompType.LT : CompType.GT; // Return LT if A < B, otherwise GT
    }

    // Calculate the circumcircle radius
    public static double calculateCircumcircleRadius(Point p1, Point p2, Point p3) {
        double a = Point.distanceOf(p1, p2);
        double b = Point.distanceOf(p2, p3);
        double c = Point.distanceOf(p1, p3);

        // If the points are collinear
        if (areCollinear(p1, p2, p3)) {
            // The circumcircle radius equals to half of the longest edge.
            return Math.max(a, Math.max(b, c)) / 2.0;
        }

        // Calculate the area of the triangle
        double s = (a + b + c) / 2.0; // Half circumference
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c)); // Heron's formula

        // r = (a * b * c) / (4 * area)
        return (a * b * c) / (4.0 * area);
    }

    // Determine whether the points are collinear or not
    private static boolean areCollinear(Point p1, Point p2, Point p3) {
        // By area, collinear if the area is zero
        return Math.abs(
                (p1.getX() * (p2.getY() - p3.getY()) +
                        p2.getX() * (p3.getY() - p1.getY()) +
                        p3.getX() * (p1.getY() - p2.getY())) / 2.0) < 1e-6; // threshold
    }

    public static double calculateTriangleArea(Point p1, Point p2, Point p3) {
        return 0.5 * Math.abs(
                p1.getX() * (p2.getY() - p3.getY()) +
                p2.getX() * (p3.getY() - p1.getY()) +
                p3.getX() * (p1.getY() - p2.getY()));
    }

    // Calculates the angle formed by three points (p1, p2, p3) where p2 is the
    // vertex.
    public static double calculateAngle(Point p1, Point p2, Point p3) {
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
