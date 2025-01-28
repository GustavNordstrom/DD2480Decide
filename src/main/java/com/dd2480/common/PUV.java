package com.dd2480.common;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(using = PUVDeserializer.class)
public class PUV {
    private final List<Boolean> puv;

    public PUV(List<Boolean> l) {
        // puv = l;
        puv = new ArrayList<>(l);
    }

    public PUV(Builder builder) {
        this.puv = builder.puv;
    }

    public List<Boolean> getVector() {
        return puv;
    }

    // @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
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
