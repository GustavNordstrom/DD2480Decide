package com.dd2480.CMV.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.Parameters;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConditionOneTest {

    private ConditionOne conditionOne;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionOne = new ConditionOne(); // Instance of the condition to test
        conditionContext = mock(ConditionContext.class); // Mock the context
    }

    @Test
    public void testEvaluate_conditionMet() {
        // Set up the mock behavior for ConditionContext
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points that cannot all fit within a circle of radius RADIUS1
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(4.0, 0.0));
        pointCollection.addPoint(new Point(8.0, 8.0)); // Forms a triangle with large circumcircle radius
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set up RADIUS1 to be 5.0 (this will make the condition true)
        when(params.getRADIUS1()).thenReturn(5.0);

        // Evaluate the condition
        boolean result = conditionOne.evaluate(conditionContext);

        // Assert that the condition is met
        assertTrue(result);
    }

    @Test
    public void testEvaluate_conditionNotMet() {
        // Set up the mock behavior for ConditionContext
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points that all fit within a circle of radius RADIUS1
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(2.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 1.0)); // Forms a small triangle
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set up RADIUS1 to be 5.0 (the condition will not be met)
        when(params.getRADIUS1()).thenReturn(5.0);

        // Evaluate the condition
        boolean result = conditionOne.evaluate(conditionContext);

        // Assert that the condition is not met
        assertFalse(result);
    }

    @Test
    public void testEvaluate_insufficientPoints() {
        // Set up the mock behavior for ConditionContext
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add fewer than 3 points
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(4.0, 0.0));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set up RADIUS1 to any value
        when(params.getRADIUS1()).thenReturn(5.0);

        // Evaluate the condition
        boolean result = conditionOne.evaluate(conditionContext);

        // Assert that the condition is not met
        assertFalse(result);
    }
}

