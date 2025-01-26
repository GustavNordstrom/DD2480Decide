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

public class ConditionElevenTest {

    private ConditionEleven conditionEleven;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionEleven = new ConditionEleven();
        conditionContext = mock(ConditionContext.class);
    }

    @Test
    public void testEvaluate_conditionMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points such that X[j] - X[i] < 0
        pointCollection.addPoint(new Point(5.0, 0.0)); // P1
        pointCollection.addPoint(new Point(1.0, 1.0)); // Intermediate point
        pointCollection.addPoint(new Point(3.0, 3.0)); // P2
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set G_PTS = 1
        when(params.getGPTS()).thenReturn(1);

        // Evaluate the condition
        boolean result = conditionEleven.evaluate(conditionContext);

        // Meet the condition
        assertTrue(result);
    }

    @Test
    public void testEvaluate_conditionNotMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points such that X[j] - X[i] >= 0
        pointCollection.addPoint(new Point(0.0, 0.0)); // P1
        pointCollection.addPoint(new Point(1.0, 1.0)); // Intermediate point
        pointCollection.addPoint(new Point(2.0, 2.0)); // P2
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set G_PTS = 1
        when(params.getGPTS()).thenReturn(1);

        // Evaluate the condition
        boolean result = conditionEleven.evaluate(conditionContext);

        // Do NOT meet the condition
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

        // Set G_PTS = 1
        when(params.getGPTS()).thenReturn(1);

        // Evaluate the condition
        boolean result = conditionEleven.evaluate(conditionContext);

        // Do NOT meet the condition
        assertFalse(result);
    }

    @Test
    public void testEvaluate_invalidGPTS() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add sufficient points
        pointCollection.addPoint(new Point(0.0, 0.0)); // P1
        pointCollection.addPoint(new Point(1.0, 1.0)); // Intermediate point
        pointCollection.addPoint(new Point(2.0, 2.0)); // P2
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set invalid G_PTS
        when(params.getGPTS()).thenReturn(5); // Exceeds valid range

        // Evaluate the condition
        boolean result = conditionEleven.evaluate(conditionContext);

        // Do NOT meet the condition
        assertFalse(result);
    }
}