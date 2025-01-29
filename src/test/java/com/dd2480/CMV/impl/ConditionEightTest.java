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

public class ConditionEightTest {

    private ConditionEight conditionEight;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionEight = new ConditionEight();
        conditionContext = mock(ConditionContext.class);
    }

    @Test
    public void testEvaluate_conditionMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points to form a triple that cannot be included in RADIUS1
        pointCollection.addPoint(new Point(0.0, 0.0)); // Start
        pointCollection.addPoint(new Point(1.0, 1.0)); // A_PTS Intervening point
        pointCollection.addPoint(new Point(4.0, 5.0)); // B_PTS Intervening point
        pointCollection.addPoint(new Point(5.0, 5.0)); // Additional point
        pointCollection.addPoint(new Point(6.0, 6.0)); // Additional point
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set APTS = 1, BPTS = 1, RADIUS1 = 2.0
        when(params.getAPTS()).thenReturn(1);
        when(params.getBPTS()).thenReturn(1);
        when(params.getRADIUS1()).thenReturn(2.0);

        // Meet the condition
        boolean result = conditionEight.evaluate(conditionContext);
        assertTrue(result);
    }

    @Test
    public void testEvaluate_conditionNotMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points to form a triple that can be included in RADIUS1
        pointCollection.addPoint(new Point(0.0, 0.0)); // Start
        pointCollection.addPoint(new Point(1.0, 1.0)); // A_PTS Intervening point
        pointCollection.addPoint(new Point(1.5, 1.5)); // B_PTS Intervening point
        pointCollection.addPoint(new Point(5.0, 5.0)); // Additional point
        pointCollection.addPoint(new Point(6.0, 6.0)); // Additional point
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set APTS = 1, BPTS = 1, RADIUS1 = 5.0
        when(params.getAPTS()).thenReturn(1);
        when(params.getBPTS()).thenReturn(1);
        when(params.getRADIUS1()).thenReturn(5.0);

        // Do NOT meet the condition
        boolean result = conditionEight.evaluate(conditionContext);
        assertFalse(result);
    }

    @Test
    public void testEvaluate_insufficientPoints() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add less than 3 points
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 1.0));
        pointCollection.addPoint(new Point(1.5, 1.5));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        when(params.getAPTS()).thenReturn(1);
        when(params.getBPTS()).thenReturn(1);
        when(params.getRADIUS1()).thenReturn(5.0);

        // Evaluate the condition
        boolean result = conditionEight.evaluate(conditionContext);

        // Do NOT meet the condition
        assertFalse(result);
    }

}