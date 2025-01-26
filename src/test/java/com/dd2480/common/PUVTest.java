package com.dd2480.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PUVTest {
    @Test
    public void setPUVCreatation() {
        int defaultLength = PUV.Builder.length;
        int lastIdx = defaultLength - 1;
        PUV puv = new PUV.Builder()
                .setVal(0, Boolean.TRUE)
                .setVal(1, Boolean.TRUE)
                .setVal(lastIdx - 1, Boolean.TRUE)
                .setVal(lastIdx, Boolean.TRUE)
                .build();

        List<Boolean> targetVec = new ArrayList<>();
        for (int i = 0; i < defaultLength; ++i) {
            if (i == 0 || i == 1 || i == lastIdx - 1 || i == lastIdx) {
                targetVec.add(Boolean.TRUE);
            } else {
                targetVec.add(Boolean.FALSE);
            }
        }

        PUV targetPUV = new PUV(targetVec);
        Assertions.assertEquals(targetPUV.getVector(), puv.getVector());
    }
}
