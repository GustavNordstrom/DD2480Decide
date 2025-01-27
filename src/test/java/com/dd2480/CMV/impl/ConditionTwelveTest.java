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

public class ConditionTwelveTest {

    private ConditionTwelve conditionTwelve;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionTwelve = new ConditionTwelve();
        conditionContext = mock(ConditionContext.class);
    }

    @Test
    public void testEvaluate_conditionMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points satisfying both conditions
        pointCollection.addPoint(new Point(0.0, 0.0)); // P1
        pointCollection.addPoint(new Point(1.0, 1.0)); // Intermediate point
        pointCollection.addPoint(new Point(5.0, 5.0)); // P2 (distance > LENGTH1)
        pointCollection.addPoint(new Point(3.0, 3.0)); // Intermediate point
        pointCollection.addPoint(new Point(3.5, 3.5)); // P3 (distance < LENGTH2)
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set K_PTS = 1, LENGTH1 = 3.0, LENGTH2 = 2.5
        when(params.getKPTS()).thenReturn(1);
        when(params.getLENGTH1()).thenReturn(3.0);
        when(params.getLENGTH2()).thenReturn(2.5);

        // Evaluate the condition
        boolean result = conditionTwelve.evaluate(conditionContext);

        // Meet the condition
        assertTrue(result);
    }

    @Test
    public void testEvaluate_conditionNotMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points not satisfying both conditions
        pointCollection.addPoint(new Point(0.0, 0.0)); // P1
        pointCollection.addPoint(new Point(1.0, 1.0)); // Intermediate point
        pointCollection.addPoint(new Point(2.0, 2.0)); // P2
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set K_PTS = 1, LENGTH1 = 5.0, LENGTH2 = 3.0
        when(params.getKPTS()).thenReturn(1);
        when(params.getLENGTH1()).thenReturn(5.0); // distance < LENGTH1 (condition A false)
        when(params.getLENGTH2()).thenReturn(3.0); // distance < LENGTH2 (condition B true)

        // Evaluate the condition
        boolean result = conditionTwelve.evaluate(conditionContext);

        // Do NOT meet the condition
        assertFalse(result);
    }

    @Test
    public void testEvaluate_insufficientPoints() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add fewer than 3 points
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 1.0));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set K_PTS = 1, LENGTH1 = 3.0, LENGTH2 = 1.5
        when(params.getKPTS()).thenReturn(1);
        when(params.getLENGTH1()).thenReturn(3.0);
        when(params.getLENGTH2()).thenReturn(1.5);

        // Evaluate the condition
        boolean result = conditionTwelve.evaluate(conditionContext);

        // Do NOT meet the condition
        assertFalse(result);
    }
}