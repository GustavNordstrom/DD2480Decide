package com.dd2480.PUM.impl;

import com.dd2480.CMV.CMV;
import com.dd2480.PUM.PUM;
import com.dd2480.PUM.PUMManager;
import com.dd2480.common.Connector;
import com.dd2480.common.LCM;

import java.util.ArrayList;

/**
 * Implementation of PUMManager
 */
public class PUMManagerImpl implements PUMManager {
    private PUM pum;
    private CMV cmv;
    private LCM lcm;
    @Override
    public PUM getPUM() {
        return this.pum;
    }

    /**
     * Constructs a PUMMangerImpl object with cmv and lcm object
     * @param cmv
     * @param lcm
     */
    public PUMManagerImpl(CMV cmv, LCM lcm) {
        // Validate inputs
        if (cmv == null) {
            throw new IllegalArgumentException("CMV cannot be null");
        }
        if (lcm == null) {
            throw new IllegalArgumentException("LCM cannot be null");
        }

        if (cmv.getVector().size() != lcm.getMatrix().get(0).size() ||
            lcm.getMatrix().size() != lcm.getMatrix().get(0).size()) {
            throw new IllegalArgumentException("wrong size of CMV and LCM");
        }

        this.pum = new PUM();
        this.cmv = cmv;
        this.lcm = lcm;
    }

    @Override
    /**
     * Compute  value of each element in the matrix
     */
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
