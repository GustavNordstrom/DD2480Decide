package com.dd2480.PUM.impl;

import com.dd2480.CMV.CMV;
import com.dd2480.PUM.PUM;
import com.dd2480.PUM.PUMManager;
import com.dd2480.common.Connector;
import com.dd2480.common.LCM;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PUMManagerImplTest {
    private CMV cmv;
    private LCM lcm;
    private PUMManager pumManager;

    @BeforeEach
    public void setUp() {
        // 1th, 2th, 3th elements are true, otherwise false
        // Table 2: Conditions Met Vector (CMV) for Example 1
        cmv = new CMV.Builder()
                .setVal(1, true)
                .setVal(2, true)
                .setVal(3, true)
                .build();

        // Table 1: Logical Connector Matrix (LCM) for Example 1
        lcm = new LCM.Builder()
                .setVal(0, 0, Connector.ANDD)
                .setVal(0, 1, Connector.ANDD)
                .setVal(0, 2, Connector.ORR)
                .setVal(0, 3, Connector.ANDD)
                .setVal(1, 0, Connector.ANDD)
                .setVal(1, 1, Connector.ANDD)
                .setVal(1, 2, Connector.ORR)
                .setVal(1, 3, Connector.ORR)
                .setVal(2, 0, Connector.ORR)
                .setVal(2, 1, Connector.ORR)
                .setVal(2, 2, Connector.ANDD)
                .setVal(2, 3, Connector.ANDD)
                .setVal(3, 0, Connector.ANDD)
                .setVal(3, 1, Connector.ORR)
                .setVal(3, 2, Connector.ANDD)
                .setVal(3, 3, Connector.ANDD)
                .build();
        pumManager = new PUMManagerImpl(cmv, lcm);
    }

    @Test
    public void testComputePUM() {
        pumManager.computePUM();
        PUM pum = pumManager.getPUM();
        ArrayList<ArrayList<Boolean>> matrix = pum.getMatrix();
        Assertions.assertFalse(matrix.get(0).get(1), "PUM[0,1] is false because LCM[0,1] is ANDD, and at least one of CMV[0] and CMV[1] is\n" +
                "false.");

        Assertions.assertTrue(matrix.get(0).get(2), "PUM[0,2] is true because LCM[0,2] is ORR, and at least one of CMV[0] and CMV[2] is\n" +
                "true.");

        Assertions.assertTrue(matrix.get(1).get(2), "PUM[1,2] is true because LCM[1,2] is ORR, and at least one of CMV[1] and CMV[2] is\n" +
                "true");

        Assertions.assertTrue(matrix.get(2).get(3), "PUM[2,3] is true because LCM[2,3] is ANDD, and both CMV[2] and CMV[3] are true.");

        Assertions.assertTrue((matrix.get(0).get(4)), "PUM[0,4] is true because LCM[0,4] is NOTUSED.");

    }

    @Test
    public void testNullArguments1() {
        // Use assertThrows to check if IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            pumManager = new PUMManagerImpl(cmv, null);  // Constructor should throw the exception
        });
    }

    @Test
    public void testNullArguments2() {
        // Use assertThrows to check if IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            pumManager = new PUMManagerImpl(null, lcm);  // Constructor should throw the exception
        });
    }
}