package com.dd2480.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class LCMTest {
    @Test
    public void testCreateLCM() {
        LCM lcm = new LCM.Builder()
                .setVal(0, 0, Connector.ORR)
                .setVal(14, 14, Connector.ANDD).build();

        ArrayList<ArrayList<Connector>> matrix = lcm.getMatrix();
        for (int i = 0; i < matrix.size(); ++i)
            for (int j = 0; j < matrix.get(0).size(); ++j) {
                Connector val = matrix.get(i).get(j);
                if (i == 0 && j == 0) {
                    Assertions.assertEquals(val, Connector.ORR);
                    continue;
                }

                if (i == 14 && j == 14) {
                    Assertions.assertEquals(val, Connector.ANDD);
                    continue;
                }

                Assertions.assertEquals(val, Connector.NOTUSED);
            }
    }
}
