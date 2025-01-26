package com.dd2480.FUV.impl;

import com.dd2480.FUV.FUV;
import com.dd2480.FUV.FUVManager;
import com.dd2480.PUM.PUM;
import com.dd2480.common.PUV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FUVManagerImplTest {
    private PUM pum;
    private PUV puv;

    @BeforeEach
    public void setUp() {
        pum = new PUM();
        pum.setAllTrue();
        pum.setVal(0, 1, Boolean.FALSE);
        pum.setVal(0, 3, Boolean.FALSE);
        pum.setVal(1, 0, Boolean.FALSE);
        pum.setVal(3, 0, Boolean.FALSE);

        puv = new PUV.Builder()
                .setVal(0, Boolean.TRUE)
                .setVal(1, Boolean.FALSE)
                .setVal(2, Boolean.TRUE)
                .build();
    }

    @Test
    public void testFUVCompute() {
        FUVManager fuvManager = new FUVManagerImpl(pum, puv);
        fuvManager.computeFUV();
        FUV computedFUV = fuvManager.getFUV();

        List<Boolean> targetVec = new ArrayList<>();
        int targetLength = 15;
        for (int i = 0; i < targetLength; ++i) {
            if (i == 0) {
                targetVec.add(Boolean.FALSE);
            } else {
                targetVec.add(Boolean.TRUE);
            }
        }

        Assertions.assertEquals(targetVec, computedFUV.getVector());

    }
}