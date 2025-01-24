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

public class ConditionThreeTest {

    private ConditionThree conditionThree;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionThree = new ConditionThree();
        conditionContext = mock(ConditionContext.class);
    }

    @Test
    public void testEvaluate_conditionMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points, the triangle area is 8
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(4.0, 0.0));
        pointCollection.addPoint(new Point(2.0, 4.0));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set AREA1
        when(params.getAREA1()).thenReturn(5.0);

        // Meet the condition
        boolean result = conditionThree.evaluate(conditionContext);
        assertTrue(result);
    }

    @Test
    public void testEvaluate_conditionNotMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points, the triangle area is 1
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(2.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 1.0));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set AREA1
        when(params.getAREA1()).thenReturn(5.0);

        // Do NOT meet the condition
        boolean result = conditionThree.evaluate(conditionContext);
        assertFalse(result);
    }

    @Test
    public void testEvaluate_insufficientPoints() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add less than three points
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 1.0));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set AREA1
        when(params.getAREA1()).thenReturn(5.0);

        // Do NOT meet the condition
        boolean result = conditionThree.evaluate(conditionContext);
        assertFalse(result);
    }
}
