package com.dd2480.FUV.impl;

import com.dd2480.FUV.FUV;
import com.dd2480.FUV.FUVManager;
import com.dd2480.PUM.PUM;
import com.dd2480.common.PUV;

import java.util.List;

/**
 * Implementation of interface FUVManager
 */
public class FUVManagerImpl implements FUVManager {
    private FUV fuv;
    private PUM pum;
    private PUV puv;

    /**
     * Construct a FUVManagerImpl object with pum and puv objects
     * @param pum a PUM object
     * @param puv a PUV object
     */
    public FUVManagerImpl(PUM pum, PUV puv) {
        if (pum == null || puv == null) {
            throw new IllegalArgumentException("pum or puv is null");
        }

        if (pum.getMatrix().size() != pum.getMatrix().get(0).size() ||
            pum.getMatrix().size() != puv.getVector().size()) {
            throw new IllegalArgumentException("wrong size pum or puv");
        }
        fuv = new FUV();
        this.pum = pum;
        this.puv = puv;

    }
    @Override
    /**
     * Calculate internal FUV object
     */
    public void computeFUV() {
        for (int i = 0; i < fuv.getVector().size(); ++i) {
            if (!puv.getVector().get(i)) {
                fuv.setVal(i, true);
                continue;
            }

            List<Boolean> pumIthRow = pum.getMatrix().get(i);

            int finalI = i;
            Boolean val = pumIthRow.stream()
                            .filter(b -> pumIthRow.indexOf(b) != finalI)
                                    .allMatch(Boolean::booleanValue);

            fuv.setVal(i, val);
        }
    }

    @Override
    public FUV getFUV() {
        return fuv;
    }
}
