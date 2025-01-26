package com.dd2480.common;


import java.util.ArrayList;
import java.util.List;

public class PUV {
    private List<Boolean> puv;
    public PUV(List<Boolean> l) {
        puv = l;
    }

    public PUV(Builder builder) {
        this.puv = builder.puv;
    }
    public List<Boolean> getVector() {
        return puv;
    }
    public static class Builder {
        private List<Boolean> puv;
        public static final int length = 15;
        public Builder() {
            this.puv = new ArrayList<Boolean>();
            for (int i = 0; i < length; ++i) {
                this.puv.add(Boolean.FALSE);
            }
        }

        public Builder setVal(int idx, Boolean val) {
            this.puv.set(idx, val);
            return this;
        }

        public PUV build() {
            return new PUV(this);
        }
    }
}
