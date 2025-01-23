package com.dd2480.CMV.impl;

import com.dd2480.CMV.Condition;
import com.dd2480.CMV.ConditionContext;

import java.util.ArrayList;
import java.util.List;

public class ConditionManagerImpl {
    private final List<Condition> conditions;
    public List<Boolean> conditionMetVector;

    public ConditionManagerImpl(List<Condition> conditions) {
        this.conditions = conditions;
        int numberConditions = conditions.size();
        this.conditionMetVector = new ArrayList<>(numberConditions);
        for (int i = 0; i < numberConditions; ++ i) {
            this.conditionMetVector.add(false);
        }
    }
    // Getters for the conditions and conditionMetVector (if needed)
    public List<Condition> getConditions() {
        return conditions;
    }

    public List<Boolean> getConditionMetVector() {
        return conditionMetVector;
    }



    public void evaluateAll(ConditionContext conditionContext) {
        for (int i = 0; i < conditions.size(); ++i) {
            if (conditions.get(i).evaluate(conditionContext)) {
                conditionMetVector.set(i, true);
            }
        }
    }

    public void addCondition(Condition condition) {
        conditions.add(condition);
    }
}