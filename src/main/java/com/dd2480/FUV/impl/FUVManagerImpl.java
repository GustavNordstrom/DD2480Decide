package com.dd2480.FUV.impl;

import com.dd2480.FUV.FUV;
import com.dd2480.FUV.FUVManager;
import com.dd2480.PUM.PUM;
import com.dd2480.common.PUV;

public class FUVManagerImpl implements FUVManager {
    private FUV fuv;
    private PUM pum;
    private PUV puv;
    public FUVManagerImpl(PUM pum, PUV puv) {
        fuv = new FUV();
        this.pum = pum;
        this.puv = puv;

    }
    @Override
    public void computeFUV() {
        for (int i = 0; i < fuv.getVector().size(); ++i) {
            if (!puv.getVector().get(i)) {
                fuv.setVal(i, true);
                continue;
            }
            Boolean val = puv.getVector().stream().allMatch(Boolean::valueOf);
            fuv.setVal(i, val);
        }
    }

    @Override
    public FUV getFUV() {
        return null;
    }
}
