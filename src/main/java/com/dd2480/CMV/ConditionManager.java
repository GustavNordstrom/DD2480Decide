package com.dd2480.CMV;

import java.util.List;

public interface ConditionManager {
    List<Condition> getConditions();

    List<Boolean> getConditionMetVector();


    void evaluateAll(ConditionContext conditionContext);

    void addCondition(Condition condition);
}