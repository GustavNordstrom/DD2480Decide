package com.dd2480.PUM;

import java.util.ArrayList;

public class PUM {
    private final int rowNumber = 15;
    private final int colNumber = 15;
    private ArrayList<ArrayList<Boolean>> preliminaryUnlockingMatrix;
    public PUM(ArrayList<ArrayList<Boolean>> pum) {
        this.preliminaryUnlockingMatrix = pum;
    }

    public PUM() {
        // all false by default
        preliminaryUnlockingMatrix = new ArrayList<ArrayList<Boolean>>();
        for (int i = 0; i < rowNumber; ++i) {
            ArrayList<Boolean> l = new ArrayList<>();
            for (int j = 0; j < colNumber; ++i) {
                l.add(false);
            }
            preliminaryUnlockingMatrix.add(l);
        }
    }
    ArrayList<ArrayList<Boolean>> getMatrix() {
        return preliminaryUnlockingMatrix;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public  int getColNumber() {
        return  colNumber;
    }

    public void setVal(int i, int j, Boolean val) {
        preliminaryUnlockingMatrix.get(i).set(j, val);
    }
}