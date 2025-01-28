package com.dd2480.CMV.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.Parameters;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;

public class ConditionThirteenTest {

    private ConditionThirteen conditionThirteen;
    private ConditionContext conditionContext;

    @BeforeEach
    public void setUp() {
        conditionThirteen = new ConditionThirteen();
        conditionContext = mock(ConditionContext.class);
    }

    @Test
    public void testEvaluate_condition1NotMet(){
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Points 0, 2 and 4 are collinear and can be contained on a circle of radius 1
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(0.0, 0.0)); 
        pointCollection.addPoint(new Point(1.0, 0.0));
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(2.0, 0.0));  
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

         // Set APTS = 1, BPTS = 1, RADIUS1 = 1.0, RADIUS2 = 1.0
        when(params.getAPTS()).thenReturn(1);
        when(params.getBPTS()).thenReturn(1);
        when(params.getRADIUS1()).thenReturn(1.0);
        when(params.getRADIUS2()).thenReturn(1.0);

        // Meet the condition
        boolean result = conditionThirteen.evaluate(conditionContext);
        assertFalse(result);
    }

    @Test
    public void testEvaluate_condition2NotMet() {
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Points 0, 2 and 4 cannot be contained within a circle of radius 2
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(0.0, 0.0)); 
        pointCollection.addPoint(new Point(3.0, 0.0));
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(4.0, 2.5));  
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

         // Set APTS = 1, BPTS = 1, RADIUS1 = 2.0, RADIUS2 = 2.0
        when(params.getAPTS()).thenReturn(1);
        when(params.getBPTS()).thenReturn(1);
        when(params.getRADIUS1()).thenReturn(2.0);
        when(params.getRADIUS2()).thenReturn(2.0);

        // Meet the condition
        boolean result = conditionThirteen.evaluate(conditionContext);
        assertFalse(result);
    }

    @Test
    public void testEvaluate_conditionsMet(){
        Parameters params = mock(Parameters.class);
        PointCollection pointCollection = new PointCollection();

        // Points 0, 2 and 5 cannot be contained within a circle of radius 2
        // Points 1, 3 and 6 can be contained within a circle of radius 1
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(0.0, 0.0)); 
        pointCollection.addPoint(new Point(3.0, -2.0));
        pointCollection.addPoint(new Point(1.0, 0.0));
        pointCollection.addPoint(new Point(0.0, 0.0));  
        pointCollection.addPoint(new Point(1.5, 2.5));
        pointCollection.addPoint(new Point(0.5, 0.9));
        when(conditionContext.getParameters()).thenReturn(params);
        when(conditionContext.getPointCollection()).thenReturn(pointCollection);

         // Set APTS = 1, BPTS = 2, RADIUS1 = 2.0, RADIUS2 = 1.0
        when(params.getAPTS()).thenReturn(1);
        when(params.getBPTS()).thenReturn(2);
        when(params.getRADIUS1()).thenReturn(2.0);
        when(params.getRADIUS2()).thenReturn(1.0);

        // Meet the condition
        boolean result = conditionThirteen.evaluate(conditionContext);
        assertTrue(result);
    }
}
