package com.dd2480.FUV;

import java.util.ArrayList;
import java.util.List;

public class FUV {
    private List<Boolean> fuv;
    private final int length = 15;

    public FUV(List<Boolean> f) {
        fuv = f;
    }

    public FUV() {
        ArrayList<Boolean> l = new ArrayList<>();
        for (int i = 0; i < length; ++i) {
            l.add(false);
        }
        fuv = l;
    }
    public List<Boolean> getVector() {
        return fuv;
    }

    public void setVal(int idx, Boolean val) {
        fuv.set(idx, val);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Boolean e: fuv) {
            sb.append(e.toString()).append(' ');
        }

        sb.append('\n');
        return sb.toString();
    }
}
