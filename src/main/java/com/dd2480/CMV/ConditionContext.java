package com.dd2480.CMV;

import com.dd2480.common.Parameters;
import com.dd2480.common.PointCollection;

/**
 * Interface for providing context to condition evaluations, 
 * which are the parameters and points of the decide problem.
 */
public interface ConditionContext {
    /**
     * Returns the parameters used in the condition evaluation.
     *
     * @return the parameters
     */
    Parameters getParameters();

    /**
     * Returns the point collection used in the condition evaluation.
     *
     * @return the point collection
     */
    PointCollection getPointCollection();
}