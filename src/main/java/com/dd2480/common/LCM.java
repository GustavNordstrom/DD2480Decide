package com.dd2480.common;

import java.util.ArrayList;

/*
Logical Connector Matrix
 */
public class LCM {
    ArrayList<ArrayList<Connector>> matrix;
    public LCM(ArrayList<ArrayList<Connector>> matrix) {
        this.matrix = matrix;
    }
    public LCM(Builder builder) {this.matrix = builder.matrix;}

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
