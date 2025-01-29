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
            for (int j = 0; j < colNumber; ++j) {
                l.add(false);
            }
            preliminaryUnlockingMatrix.add(l);
        }
    }
    public ArrayList<ArrayList<Boolean>> getMatrix() {
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
    public void setAllTrue() {
        for (ArrayList<Boolean> unlockingMatrix : preliminaryUnlockingMatrix) {
            for (int j = 0; j < preliminaryUnlockingMatrix.get(0).size(); ++j) {
                unlockingMatrix.set(j, Boolean.TRUE);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ArrayList<Boolean> unlockingMatrix : preliminaryUnlockingMatrix) {
            for (int j = 0; j < preliminaryUnlockingMatrix.get(0).size(); ++j) {
                sb.append(unlockingMatrix.get(j).toString())
                        .append(' ');
            }
            sb.append('\n');
        }

        sb.append('\n');
        return sb.toString();
    }

}
