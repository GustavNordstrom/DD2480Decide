package com.dd2480.CMV.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the 5th condition by one valid test case and three invalid test cases, 
 * one invalid test case is "Add points, do NOT meet the condition X[j] - X[i] < 0" and 
 * the 2nd is "NUMPOINTS < 2"
 * the last is "equal X coordinates"
 */
public class ConditionFiveTest {

    private ConditionFive conditionFive;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionFive = new ConditionFive();
        conditionContext = mock(ConditionContext.class);
    }

    @Test
    public void testEvaluate_conditionMet() {
        PointCollection pointCollection = new PointCollection();

        // Add points, meet the condition X[j] - X[i] < 0
        pointCollection.addPoint(new Point(2.0, 1.0));
        pointCollection.addPoint(new Point(1.0, 1.0));
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Meet the condition
        boolean result = conditionFive.evaluate(conditionContext);
        assertTrue(result);
    }

    @Test
    public void testEvaluate_conditionNotMet() {
        PointCollection pointCollection = new PointCollection();

        // Add points, do NOT meet the condition X[j] - X[i] < 0
        pointCollection.addPoint(new Point(1.0, 1.0));
        pointCollection.addPoint(new Point(2.0, 1.0));
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Do NOT meet the condition
        boolean result = conditionFive.evaluate(conditionContext);
        assertFalse(result);
    }

    @Test
    public void testEvaluate_insufficientPoints() {
        PointCollection pointCollection = new PointCollection();

        // Add less than 2 points
        pointCollection.addPoint(new Point(1.0, 1.0));
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Do NOT meet the condition
        boolean result = conditionFive.evaluate(conditionContext);
        assertFalse(result);
    }

    @Test
    public void testEvaluate_equalXCoordinates() {
        PointCollection pointCollection = new PointCollection();

        // Add points, x_i = x_j
        pointCollection.addPoint(new Point(1.0, 1.0));
        pointCollection.addPoint(new Point(1.0, 2.0));
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Do NOT meet the condition
        boolean result = conditionFive.evaluate(conditionContext);
        assertFalse(result);
    }
}
