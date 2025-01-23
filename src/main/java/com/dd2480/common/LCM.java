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

    public ArrayList<ArrayList<Connector>> getMatrix() {
        return matrix;
    }
}
