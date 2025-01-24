package com.dd2480.PUM.impl;

import com.dd2480.CMV.CMV;
import com.dd2480.PUM.PUM;
import com.dd2480.PUM.PUMManager;
import com.dd2480.common.Connector;
import com.dd2480.common.LCM;

import java.util.ArrayList;

public class PUMManagerImpl implements PUMManager {
    private PUM pum;
    private CMV cmv;
    private LCM lcm;
    @Override
    public PUM getPUM() {
        return this.pum;
    }

    public PUMManagerImpl(CMV cmv, LCM lcm) {
        this.pum = new PUM();
        this.cmv = cmv;
        this.lcm = lcm;
    }

    @Override
    public void computePUM() {
        for (int i = 0; i < pum.getRowNumber(); ++i) {
            for (int j = 0; j < pum.getColNumber(); ++j) {
                Connector c = lcm.getMatrix().get(i).get(j);
                if (c == Connector.NOTUSED) {
                    pum.setVal(i, j, true);
                    continue;
                }

                if (c == Connector.ANDD) {
                    Boolean val_i = cmv.getVector().get(i);
                    Boolean val_j = cmv.getVector().get(j);
                    pum.setVal(i, j, val_i & val_j);
                } else if (c == Connector.ORR) {
                    Boolean val_i = cmv.getVector().get(i);
                    Boolean val_j = cmv.getVector().get(j);
                    pum.setVal(i, j, val_i | val_j);
                }
            }
        }
    }
}
