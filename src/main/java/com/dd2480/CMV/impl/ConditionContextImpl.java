package com.dd2480.CMV.impl;

import com.dd2480.CMV.ConditionContext;
import com.dd2480.common.Parameters;
import com.dd2480.common.PointCollection;

/**
 * Implementation of the ConditionContext interface.
 * This class holds the parameters and point collection used for condition evaluation.
 */
public class ConditionContextImpl implements ConditionContext {
    private final Parameters params;
    private final PointCollection pointCollection;

    /**
     * Constructs a ConditionContextImpl with the specified parameters and point collection.
     *
     * @param parameters the parameters to be used
     * @param pointCollection the point collection to be used
     */
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
