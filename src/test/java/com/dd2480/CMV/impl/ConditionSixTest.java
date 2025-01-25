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

public class ConditionSixTest {

    private ConditionSix conditionSix;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionSix = new ConditionSix();
        conditionContext = mock(ConditionContext.class);
    }

    @Test
    public void testEvaluate_conditionMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points where the distance from one point to the line is greater than DIST
        pointCollection.addPoint(new Point(0.0, 0.0)); // Start
        pointCollection.addPoint(new Point(1.0, 2.0)); // Mid
        pointCollection.addPoint(new Point(2.0, 0.0)); // End
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set NPTS = 3, DIST = 1.0
        when(params.getNPTS()).thenReturn(3);
        when(params.getDIST()).thenReturn(1.0);

        // Meet the condition
        boolean result = conditionSix.evaluate(conditionContext);
        assertTrue(result);
    }

    @Test
    public void testEvaluate_conditionNotMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points, all of which have a distance from the line less than or equal to
        // DIST
        pointCollection.addPoint(new Point(0.0, 0.0)); // Start
        pointCollection.addPoint(new Point(1.0, 0.5)); // Mid
        pointCollection.addPoint(new Point(2.0, 0.0)); // End
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set NPTS = 3, DIST = 2.0
        when(params.getNPTS()).thenReturn(3);
        when(params.getDIST()).thenReturn(2.0);

        // Do NOT meet the condition
        boolean result = conditionSix.evaluate(conditionContext);
        assertFalse(result);
    }

    @Test
    public void testEvaluate_insufficientPoints() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points less than 3
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 1.0));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set NPTS = 3, DIST = 1.0
        when(params.getNPTS()).thenReturn(3);
        when(params.getDIST()).thenReturn(1.0);

        // Do NOT meet the condition
        boolean result = conditionSix.evaluate(conditionContext);
        assertFalse(result);
    }
}
