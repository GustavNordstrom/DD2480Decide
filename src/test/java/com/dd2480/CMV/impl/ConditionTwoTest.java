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

public class ConditionTwoTest {

    private ConditionTwo conditionTwo;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionTwo = new ConditionTwo();
        conditionContext = mock(ConditionContext.class);
    }

    @Test
    public void testEvaluate_conditionMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points
        pointCollection.addPoint(new Point(0.0, 0.0)); // first point
        pointCollection.addPoint(new Point(1.0, 1.0)); // vertex
        pointCollection.addPoint(new Point(2.0, 0.0)); // last point
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set EPSILON
        when(params.getEPSILON()).thenReturn(0.5);

        // Implement test
        boolean result = conditionTwo.evaluate(conditionContext);

        // Meet the condition
        assertTrue(result);
    }

    @Test
    public void testEvaluate_conditionNotMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points
        pointCollection.addPoint(new Point(0.0, 0.0)); // first point
        pointCollection.addPoint(new Point(1.0, 1.0)); // vertex
        pointCollection.addPoint(new Point(2.0, 1.1)); // last point, close to PI
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set EPSILON
        when(params.getEPSILON()).thenReturn(0.1);

        // Implement test
        boolean result = conditionTwo.evaluate(conditionContext);

        // Meet the condition
        assertTrue(result);
    }

    @Test
    public void testEvaluate_insufficientPoints() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add two points
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 1.0));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set EPSILON
        when(params.getEPSILON()).thenReturn(0.5);

        // Implement test
        boolean result = conditionTwo.evaluate(conditionContext);

        // Do NOT meet the condition
        assertFalse(result);
    }

    @Test
    public void testEvaluate_vertexCoincides() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points, the first point coincides with the vertex
        pointCollection.addPoint(new Point(1.0, 1.0)); // first point coincides with the vertex
        pointCollection.addPoint(new Point(1.0, 1.0)); // vertex
        pointCollection.addPoint(new Point(2.0, 2.0)); // last point
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set EPSILON
        when(params.getEPSILON()).thenReturn(0.5);

        // Implement test
        boolean result = conditionTwo.evaluate(conditionContext);

        // Do NOT meet the condition
        assertFalse(result);
    }
}
