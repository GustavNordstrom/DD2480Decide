package com.dd2480.CMV;

import com.dd2480.common.Parameters;
import com.dd2480.common.PointCollection;

public interface ConditionContext {
    Parameters getParameters();
    PointCollection getPointCollection();
    // Add more methods if needed for additional parameters
}