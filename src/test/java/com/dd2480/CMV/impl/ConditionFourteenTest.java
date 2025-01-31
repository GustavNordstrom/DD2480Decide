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
 * Test the 14th condition by one valid test case and four invalid test cases, 
 * one invalid test case is "Form triangles not meeting AREA1 but meeting AREA2" and 
 * the 2nd is "Add points that form triangles meeting AREA1 but not AREA2"
 * the 3rd is "NUMPOINTS < 5"
 * the last is "Invalid parameters as input".
 */
public class ConditionFourteenTest {

    private ConditionFourteen conditionFourteen;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionFourteen = new ConditionFourteen();
        conditionContext = mock(ConditionContext.class);
    }

    @Test
    public void testEvaluate_conditionMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points that form triangles meeting both conditions
        pointCollection.addPoint(new Point(0.0, 0.0)); // P1
        pointCollection.addPoint(new Point(1.0, 0.0)); // Intervening point
        pointCollection.addPoint(new Point(4.0, 0.0)); // P2
        pointCollection.addPoint(new Point(0.0, 3.0)); // Intervening point
        pointCollection.addPoint(new Point(2.0, 1.0)); // P3

        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set EPTS, FPTS, AREA1 and AREA2
        when(params.getEPTS()).thenReturn(1);
        when(params.getFPTS()).thenReturn(1);
        when(params.getAREA1()).thenReturn(1.0);
        when(params.getAREA2()).thenReturn(4.0);

        // Evaluate
        boolean result = conditionFourteen.evaluate(conditionContext);

        assertTrue(result);
    }

    @Test
    public void testEvaluate_conditionNotMetOnlyArea1() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // form triangles not meeting AREA1 but meeting AREA2
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 0.0));
        pointCollection.addPoint(new Point(2.0, 0.0));
        pointCollection.addPoint(new Point(0.0, 1.0));
        pointCollection.addPoint(new Point(1.0, 1.0));

        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        when(params.getEPTS()).thenReturn(1);
        when(params.getFPTS()).thenReturn(1);
        when(params.getAREA1()).thenReturn(5.0);
        when(params.getAREA2()).thenReturn(1.0);

        boolean result = conditionFourteen.evaluate(conditionContext);

        assertFalse(result);
    }

    @Test
    public void testEvaluate_conditionNotMetOnlyArea2() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add points that form triangles meeting AREA1 but not AREA2
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 0.0));
        pointCollection.addPoint(new Point(4.0, 0.0));
        pointCollection.addPoint(new Point(0.0, 3.0));
        pointCollection.addPoint(new Point(10.0, 10.0));

        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        when(params.getEPTS()).thenReturn(1);
        when(params.getFPTS()).thenReturn(1);
        when(params.getAREA1()).thenReturn(4.0);
        when(params.getAREA2()).thenReturn(0.5);

        boolean result = conditionFourteen.evaluate(conditionContext);

        assertFalse(result);
    }

    @Test
    public void testEvaluate_insufficientPoints() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add fewer than 5 points
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 0.0));
        pointCollection.addPoint(new Point(2.0, 0.0));

        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        when(params.getEPTS()).thenReturn(1);
        when(params.getFPTS()).thenReturn(1);
        when(params.getAREA1()).thenReturn(2.0);
        when(params.getAREA2()).thenReturn(1.0);

        boolean result = conditionFourteen.evaluate(conditionContext);

        assertFalse(result);
    }

    @Test
    public void testEvaluate_invalidParameters() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Add valid points
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(1.0, 0.0));
        pointCollection.addPoint(new Point(4.0, 0.0));
        pointCollection.addPoint(new Point(0.0, 3.0));
        pointCollection.addPoint(new Point(2.0, 1.0));

        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

        // Set invalid parameters, AREA2 < 0, EPTS + FPTS out of range
        when(params.getEPTS()).thenReturn(1);
        when(params.getFPTS()).thenReturn(1);
        when(params.getAREA1()).thenReturn(4.0);
        when(params.getAREA2()).thenReturn(-1.0); // Invalid: Negative AREA2

        // Evaluate the condition
        boolean result = conditionFourteen.evaluate(conditionContext);

        // Condition should NOT be met
        assertFalse(result);
    }
}
