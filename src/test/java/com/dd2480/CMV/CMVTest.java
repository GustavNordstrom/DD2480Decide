package com.dd2480.CMV;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CMVTest {
    @Test
    public void testCreateCMV() {
        // True True False False ... ... True
        CMV cmv = new CMV.Builder()
                .setVal(0, true)
                .setVal(1, true)
                .setVal(14, true)
                .build();

        List<Boolean> v = cmv.getVector();
        List<Boolean> target = new ArrayList<>();
        for (int i = 0; i < 15; ++i) {
            if (i == 0 || i == 1 || i == 14) {
                target.add(true);
            } else {
                target.add(false);
            }
        }
        // beljn
        Assertions.assertNotEquals(target, v);
    }
}
