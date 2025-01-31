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

/**
 * Test the zero condition by one valid test case and two invalid test cases, 
 * one invalid test case is "Distance between the two points <= LENGTH1" and 
 * the 2nd is "NUMPOINTS < 2".
 */
public class ConditionZeroTest {

    private ConditionZero conditionZero;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionZero = new ConditionZero(); // Instance of the condition to test
        conditionContext = mock(ConditionContext.class); // Mock the context
    }

    @Test
    public void testEvaluate_conditionMet() {
        // Set up the mock behavior for ConditionContext
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add some points to the point collection
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(15.0, 0.0)); // Distance > LENGTH1
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set up LENGTH1 to be 10 (this will make the condition true)
        when(params.getLENGTH1()).thenReturn(10.0);

        // Now, evaluate the condition
        boolean result = conditionZero.evaluate(conditionContext);

        // Assert that the condition is met (since the distance between the points is
        // greater than LENGTH1)
        assertTrue(result);
    }

    @Test
    public void testEvaluate_conditionNotMet() {
        // Set up the mock behavior for ConditionContext
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points to the point collection
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(5.0, 0.0)); // Distance <= LENGTH1
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set up LENGTH1 to be 10 (the condition will not be met)
        when(params.getLENGTH1()).thenReturn(10.0);

        // Now, evaluate the condition
        boolean result = conditionZero.evaluate(conditionContext);

        // Assert that the condition is not met (since the distance is <= LENGTH1)
        assertFalse(result);
    }

    @Test
    public void testEvaluate_insufficientPoints() {

        // Set up the mock behavior for ConditionContext
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points to the point collection
        pointCollection.addPoint(new Point(0.0, 0.0));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set up LENGTH1 to be 10 (the condition will not be met)
        when(params.getLENGTH1()).thenReturn(10.0);

        // Now, evaluate the condition
        boolean result = conditionZero.evaluate(conditionContext);

        // Assert that the condition is not met (since the distance is <= LENGTH1)

        assertFalse(result);
    }
}