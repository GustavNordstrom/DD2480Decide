package com.dd2480.CMV;

import java.util.ArrayList;
import java.util.List;

public class CMV {
    private List<Boolean> cmv;
    public CMV(List<Boolean> c) {
        this.cmv = c;
    }
    private CMV(Builder builder) {
        this.cmv = builder.cmv;
    }

    public List<Boolean> getVector() {
        return cmv;
    }
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
