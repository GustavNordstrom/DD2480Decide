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

/*
 * Test the 4th condition by one valid test case and two invalid test cases, 
 * one invalid test case is "Add points, distributed in the same quadrant" and 
 * the other is "Add points less than Q_PTS".
 */
public class ConditionFourTest {

    private ConditionFour conditionFour;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionFour = new ConditionFour();
        conditionContext = mock(ConditionContext.class);
    }

    @Test
    public void testEvaluate_conditionMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points, distributed in 3 quadrants
        pointCollection.addPoint(new Point(1.0, 1.0)); // 1st Quadrant
        pointCollection.addPoint(new Point(-1.0, 1.0)); // 2nd Quadrant
        pointCollection.addPoint(new Point(-1.0, -1.0)); // 3rd Quadrant
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set Q_PTS = 3，QUADS = 2
        when(params.getQPTS()).thenReturn(3);
        when(params.getQUADS()).thenReturn(2);

        // Meet the condition
        boolean result = conditionFour.evaluate(conditionContext);
        assertTrue(result);
    }

    @Test
    public void testEvaluate_conditionNotMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points, distributed in the same quadrant
        pointCollection.addPoint(new Point(1.0, 1.0)); // 1st Quadrant
        pointCollection.addPoint(new Point(2.0, 2.0)); // 1st Quadrant
        pointCollection.addPoint(new Point(3.0, 3.0)); // 1st Quadrant
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set Q_PTS = 3，QUADS = 2
        when(params.getQPTS()).thenReturn(3);
        when(params.getQUADS()).thenReturn(2);

        // Do NOT meet the condition
        boolean result = conditionFour.evaluate(conditionContext);
        assertFalse(result);
    }

    @Test
    public void testEvaluate_insufficientPoints() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points less than Q_PTS
        pointCollection.addPoint(new Point(1.0, 1.0));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set Q_PTS = 3，QUADS = 2
        when(params.getQPTS()).thenReturn(3);
        when(params.getQUADS()).thenReturn(2);

        // Do NOT meet the condition
        boolean result = conditionFour.evaluate(conditionContext);
        assertFalse(result);
    }
}
