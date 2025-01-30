package com.dd2480.common;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


/**
 * Data class of LCM
 */
@JsonDeserialize(using = LCMDeserializer.class) // Use a custom deserializer
public class LCM {
    ArrayList<ArrayList<Connector>> matrix;

    /**
     * Constructs a LCM object with a matrix
     * @param matrix
     */
    public LCM(ArrayList<ArrayList<Connector>> matrix) {
        this.matrix = matrix;
    }

    /**
     * Constructs a LCM object with builder
     * @param builder a builder object used to construct LCM objects
     */
    public LCM(Builder builder) {
        this.matrix = builder.matrix;
    }

    /**
     * Get internal matrix
     * @return a matrix with Connector type elements
     */
    public ArrayList<ArrayList<Connector>> getMatrix() {
        return matrix;
    }

    public static class Builder {
        private final int rowNumber = 15;
        private final int colNumber = 15;
        private ArrayList<ArrayList<Connector>> matrix;

        public Builder() {
            this.matrix = new ArrayList<ArrayList<Connector>>();
            for (int i = 0; i < rowNumber; ++i) {
                ArrayList<Connector> arr = new ArrayList<>();
                for (int j = 0; j < colNumber; ++j) {
                    arr.add(Connector.NOTUSED);
                }
                this.matrix.add(arr);
            }
        }

        public Builder setVal(int i, int j, Connector c) {
            this.matrix.get(i).set(j, c);
            return this;
        }

        public LCM build() {
            return new LCM(this);
        }

    }
}
