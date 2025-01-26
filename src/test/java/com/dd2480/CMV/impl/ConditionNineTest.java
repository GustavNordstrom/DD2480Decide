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

public class ConditionNineTest {

    private ConditionNine conditionNine;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionNine = new ConditionNine();
        conditionContext = mock(ConditionContext.class);
    }

    @Test
    public void testEvaluate_conditionMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points that form an angle outside the range (PI - EPSILON, PI + EPSILON)
        pointCollection.addPoint(new Point(0.0, 0.0)); // P1
        pointCollection.addPoint(new Point(1.0, 1.0)); // P2 (vertex)
        pointCollection.addPoint(new Point(2.0, 0.0)); // P3
        pointCollection.addPoint(new Point(3.0, 3.0)); // Additional point
        pointCollection.addPoint(new Point(4.0, 4.0)); // Additional point
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set C_PTS = 1, D_PTS = 1, EPSILON = 0.1
        when(params.getCPTS()).thenReturn(1);
        when(params.getDPTS()).thenReturn(1);
        when(params.getEPSILON()).thenReturn(0.1);

        // Evaluate the condition
        boolean result = conditionNine.evaluate(conditionContext);

        // Meet the condition
        assertTrue(result);
    }

    @Test
    public void testEvaluate_conditionNotMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points that form an angle within the range (PI - EPSILON, PI + EPSILON)
        pointCollection.addPoint(new Point(0.0, 0.0)); // P1
        pointCollection.addPoint(new Point(1.0, 1.0)); // P2 (vertex)
        pointCollection.addPoint(new Point(2.0, 2.0)); // P3
        pointCollection.addPoint(new Point(3.0, 3.0)); // Additional point
        pointCollection.addPoint(new Point(4.0, 4.0)); // Additional point
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set C_PTS = 1, D_PTS = 1, EPSILON = 0.1
        when(params.getCPTS()).thenReturn(1);
        when(params.getDPTS()).thenReturn(1);
        when(params.getEPSILON()).thenReturn(0.1);

        // Evaluate the condition
        boolean result = conditionNine.evaluate(conditionContext);

        // Do NOT meet the condition
        assertFalse(result);
    }

    @Test
    public void testEvaluate_insufficientPoints() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add less than 5 points
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 1.0));
        pointCollection.addPoint(new Point(2.0, 2.0));
        pointCollection.addPoint(new Point(3.0, 3.0));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set C_PTS = 1, D_PTS = 1, EPSILON = 0.1
        when(params.getCPTS()).thenReturn(1);
        when(params.getDPTS()).thenReturn(1);
        when(params.getEPSILON()).thenReturn(0.1);

        // Evaluate the condition
        boolean result = conditionNine.evaluate(conditionContext);

        // Do NOT meee the condition
        assertFalse(result);
    }

    @Test
    public void testEvaluate_vertexCoincides() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points where the vertex coincides with the first point
        pointCollection.addPoint(new Point(1.0, 1.0)); // P1
        pointCollection.addPoint(new Point(1.0, 1.0)); // P2 (vertex, coincides with P1)
        pointCollection.addPoint(new Point(2.0, 2.0)); // P3
        pointCollection.addPoint(new Point(3.0, 3.0)); // Additional point
        pointCollection.addPoint(new Point(4.0, 4.0)); // Additional point
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set C_PTS = 1, D_PTS = 1, EPSILON = 0.1
        when(params.getCPTS()).thenReturn(1);
        when(params.getDPTS()).thenReturn(1);
        when(params.getEPSILON()).thenReturn(0.1);

        // Evaluate the condition
        boolean result = conditionNine.evaluate(conditionContext);

        // Do NOT meet the condition
        assertFalse(result);
    }
}
