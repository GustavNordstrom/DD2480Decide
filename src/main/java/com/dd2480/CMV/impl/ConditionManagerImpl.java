package com.dd2480.CMV.impl;

import com.dd2480.CMV.Condition;
import com.dd2480.CMV.ConditionContext;
import com.dd2480.CMV.ConditionManager;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConditionManagerImpl implements ConditionManager {
    private final int conditionNumber = 15;
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

    private void retrieveAllConditions() {
        // Use Reflections to scan for classes implementing Condition in the 'impl' package
        Reflections reflections = new Reflections("com.dd2480.CMV.impl");  // Replace with your package
        Set<Class<? extends Condition>> conditionClasses = reflections.getSubTypesOf(Condition.class);

        // Iterate over each class and instantiate it using reflection
        for (Class<? extends Condition> conditionClass : conditionClasses) {
            try {
                // Instantiate the condition class using the default constructor
                Constructor<? extends Condition> constructor = conditionClass.getConstructor();
                Condition condition = constructor.newInstance();
                this.addCondition(condition);
                // Now you have the condition instance, and you can use it
                System.out.println("Instantiated condition: " + condition.getClass().getName());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}