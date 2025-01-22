package com.dd2480.CMV.impl;

import com.dd2480.common.Parameters;
import com.dd2480.common.Point;
import com.dd2480.common.PointCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContionZeroTest {
    private ConditionZero conditionZero;
    private Parameters params;
    private PointCollection pointCollection;

    @BeforeEach
    public void setUp() {
        // Initialize objects before each test case
        conditionZero = new ConditionZero();
        params = new Parameters.Builder()
                .setLENGTH1(9.0)
                .build();

        // Initialize point collection with some test points
        pointCollection = new PointCollection();
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(5.0, 0.0));
        pointCollection.addPoint(new Point(15.0, 0.0));
    }

    @Test
    public void testEvaluate_conditionMet() {
        // Test case where the condition should be met
        // Distance between (5.0, 0.0) and (15.0, 0.0) is greater than LENGTH1 (10.0)
        assertTrue(conditionZero.evaluate(params, pointCollection), "Condition should be met");
    }

    @Test
    public void testEvaluate_conditionNotMet() {
        // Test case where the condition should NOT be met
        // All consecutive points are within the distance of 10.0
        pointCollection = new PointCollection();
        pointCollection.addPoint(new Point(0.0, 0.0));
        pointCollection.addPoint(new Point(5.0, 0.0));
        pointCollection.addPoint(new Point(9.0, 0.0));

        // The distance between the points is not greater than LENGTH1 (10.0)
        assertFalse(conditionZero.evaluate(params, pointCollection), "Condition should NOT be met");
    }


}
