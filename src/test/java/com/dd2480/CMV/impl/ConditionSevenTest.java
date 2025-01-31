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
 * Test the 7th condition by one valid test case and three invalid test cases, 
 * one invalid test case is "The distance between two points less than LENGHTH1" and 
 * the 2nd is "NUMPOINTS < 3" and
 * the last is "Invalid input KPTS".
 */
public class ConditionSevenTest {

    private ConditionSeven conditionSeven;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionSeven = new ConditionSeven();
        conditionContext = mock(ConditionContext.class);
    }

    @Test
    public void testEvaluate_conditionMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points, which meet the condition
        pointCollection.addPoint(new Point(0.0, 0.0)); // Start
        pointCollection.addPoint(new Point(1.0, 1.0)); // Interval point
        pointCollection.addPoint(new Point(5.0, 5.0)); // Point which meets the conditions
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set KPTS = 1, LENGTH1 = 5.0
        when(params.getKPTS()).thenReturn(1);
        when(params.getLENGTH1()).thenReturn(5.0);

        // Meet the condition
        boolean result = conditionSeven.evaluate(conditionContext);
        assertTrue(result);
    }

    @Test
    public void testEvaluate_conditionNotMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points, which do not meet the condition
        pointCollection.addPoint(new Point(0.0, 0.0)); // Start
        pointCollection.addPoint(new Point(1.0, 1.0)); // Interval point
        pointCollection.addPoint(new Point(2.0, 2.0)); // Closer point
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set KPTS = 1, LENGTH1 = 5.0
        when(params.getKPTS()).thenReturn(1);
        when(params.getLENGTH1()).thenReturn(5.0);

        // Do NOT meet the condition
        boolean result = conditionSeven.evaluate(conditionContext);
        assertFalse(result);
    }

    @Test
    public void testEvaluate_insufficientPoints() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add less than 3 points
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 1.0));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set KPTS = 1, LENGTH1 = 5.0
        when(params.getKPTS()).thenReturn(1);
        when(params.getLENGTH1()).thenReturn(5.0);

        // Do NOT meet the condition
        boolean result = conditionSeven.evaluate(conditionContext);
        assertFalse(result);
    }

    @Test
    public void testEvaluate_invalidKPTS() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 1.0));
        pointCollection.addPoint(new Point(2.0, 2.0));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set invalid KPTS
        when(params.getKPTS()).thenReturn(5); // Out of range
        when(params.getLENGTH1()).thenReturn(1.0);

        // Do NOT meet the condition
        boolean result = conditionSeven.evaluate(conditionContext);
        assertFalse(result);
    }
}