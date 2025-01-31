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
 * Test the 10th condition by one valid test case and two invalid test cases, 
 * one invalid test case is "Add points that form a triangle with an area less than or equal to AREA1" and 
 * the 2nd is "NUMPOINTS < 5".
 */
public class ConditionTenTest {

    private ConditionTen conditionTen;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionTen = new ConditionTen();
        conditionContext = mock(ConditionContext.class);
    }

    @Test
    public void testEvaluate_conditionMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points that form a triangle with an area greater than AREA1
        pointCollection.addPoint(new Point(0.0, 0.0)); // P1
        pointCollection.addPoint(new Point(1.0, 1.0)); // P2 (EPTS)
        pointCollection.addPoint(new Point(4.0, 0.0)); // P3 (FPTS)
        pointCollection.addPoint(new Point(5.0, 5.0)); // Additional point
        pointCollection.addPoint(new Point(6.0, 6.0)); // Additional point
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set EPTS = 1, FPTS = 1, AREA1 = 2.0
        when(params.getEPTS()).thenReturn(1);
        when(params.getFPTS()).thenReturn(1);
        when(params.getAREA1()).thenReturn(2.0);

        // Evaluate the condition
        boolean result = conditionTen.evaluate(conditionContext);

        // Meet the condition
        assertTrue(result);
    }

    @Test
    public void testEvaluate_conditionNotMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points that form a triangle with an area less than or equal to AREA1
        pointCollection.addPoint(new Point(0.0, 0.0)); // P1
        pointCollection.addPoint(new Point(1.0, 1.0)); // P2 (EPTS)
        pointCollection.addPoint(new Point(2.0, 2.0)); // P3 (FPTS)
        pointCollection.addPoint(new Point(3.0, 3.0)); // Additional point
        pointCollection.addPoint(new Point(4.0, 4.0)); // Additional point
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set EPTS = 1, FPTS = 1, AREA1 = 5.0
        when(params.getEPTS()).thenReturn(1);
        when(params.getFPTS()).thenReturn(1);
        when(params.getAREA1()).thenReturn(5.0);

        // Evaluate the condition
        boolean result = conditionTen.evaluate(conditionContext);

        // Do NOT meet the condition
        assertFalse(result);
    }

    @Test
    public void testEvaluate_insufficientPoints() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add fewer than 5 points
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 1.0));
        pointCollection.addPoint(new Point(2.0, 2.0));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set EPTS = 1, FPTS = 1, AREA1 = 2.0
        when(params.getEPTS()).thenReturn(1);
        when(params.getFPTS()).thenReturn(1);
        when(params.getAREA1()).thenReturn(2.0);

        // Evaluate the condition
        boolean result = conditionTen.evaluate(conditionContext);

        // Do NOT meet the condition
        assertFalse(result);
    }
}