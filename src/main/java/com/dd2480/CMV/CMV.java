package com.dd2480.CMV;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the data class of CMV
 */
public class CMV {
    private List<Boolean> cmv;

    /**
     * Constructs a CMV object with a boolean list
     * @param c a boolean list
     */
    public CMV(List<Boolean> c) {
        this.cmv = c;
    }

    /**
     * Constructs a CMV object with builder
     * @param builder builder object used to initialized CMV object
     */
    private CMV(Builder builder) {
        this.cmv = builder.cmv;
    }

    /**
     * Get internal booelan vector
     * @return a boolean List
     */
    public List<Boolean> getVector() {
        return cmv;
    }

    /**
     * A static internal class used to create CMV objects
     */
    public static class Builder{
        private final int size = 15;
        private List<Boolean> cmv;
        public Builder() {
            this.cmv = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                this.cmv.add(Boolean.FALSE);
            }
        }

        public Builder setVal(int idx, Boolean val) {
            this.cmv.set(idx, val);
            return this;

        }

        public CMV build() {
            return new CMV(this);
        }

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < cmv.size(); ++i) {
            res.append(cmv.get(i).toString())
                    .append(' ');
        }

        res.append('\n');
        return res.toString();
    }
}
