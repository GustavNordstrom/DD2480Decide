package com.dd2480.CMV;

import com.dd2480.common.Parameters;
import com.dd2480.common.PointCollection;

public interface Condition {
    boolean evaluate(Parameters params, PointCollection pointCollection);
}