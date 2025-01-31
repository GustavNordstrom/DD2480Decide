package com.dd2480.CMV;

import java.util.List;

/**
 * Interface for managing and evaluating conditions.
 */
public interface ConditionManager {
    List<Condition> getConditions();
    List<Boolean> getConditionMetVector();
    void evaluateAll(ConditionContext conditionContext);
}