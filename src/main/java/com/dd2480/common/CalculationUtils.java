package com.dd2480.common;

public class CalculationUtils {

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
}
