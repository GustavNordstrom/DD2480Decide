package com.dd2480.CMV;

import com.dd2480.common.Parameters;
import com.dd2480.common.PointCollection;

import java.util.ArrayList;
import java.util.List;

public class ConditionManager {
    private final List<Condition> conditions;
    public List<Boolean> conditionMetVector;

    public ConditionManager(List<Condition> conditions) {
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



    public void evaluateAll(Parameters params, PointCollection pointCollection) {
        for (int i = 0; i < conditions.size(); ++i) {
            if (conditions.get(i).evaluate(params, pointCollection)) {
                conditionMetVector.set(i, true);
            }
        }
    }

    public void addCondition(Condition condition) {
        conditions.add(condition);
    }
}