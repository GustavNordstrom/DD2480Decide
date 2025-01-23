package com.dd2480.CMV.impl;

import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.Parameters;
import com.dd2480.common.PointCollection;

public class ConditionContextImpl implements ConditionContext {
    private final Parameters params;
    private final PointCollection pointCollection;
    public ConditionContextImpl(Parameters parameters, PointCollection pointCollection) {
        this.params = parameters;
        this.pointCollection = pointCollection;
    }
    @Override
    public Parameters getParameters() {
        return params;
    }

    @Override
    public PointCollection getPointCollection() {
        return pointCollection;
    }
}
