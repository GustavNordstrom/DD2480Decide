package com.dd2480.CMV.impl;

import com.dd2480.CMV.CMV;
import com.dd2480.CMV.Condition;
import com.dd2480.CMV.ConditionContext;
import com.dd2480.CMV.ConditionManager;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Implementation of the ConditionManager interface.
 * Manages and evaluates a list of conditions (LICs).
 */
public class ConditionManagerImpl implements ConditionManager {
    private final int conditionNumber = 15;
    private final List<Condition> conditions;
    public List<Boolean> conditionMetVector;

    /**
     * Constructs a ConditionManagerImpl that stores a list of the conditions and
     * creates a conditions met vector (CMV) which can be computed using the 
     * evaluateAll method of the ConditionManagerImpl.
     *
     * @param conditions the list of conditions to be managed
     */
    public ConditionManagerImpl(List<Condition> conditions) {
        this.conditions = conditions;
        int numberConditions = conditions.size();
        this.conditionMetVector = new ArrayList<>(numberConditions);
        for (int i = 0; i < numberConditions; ++i) {
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

    /**
     * Evaluates all conditions by calling their respective evaluate function,
     * based on the provided context. 
     *
     * @param conditionContext the context in which the conditions are evaluated
     */
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

    /**
     * Returns a CMV object representing the evaluation results.
     */
    public CMV getCMV() {
        return new CMV(conditionMetVector);
    }

    private void retrieveAllConditions() {
        // Use Reflections to scan for classes implementing Condition in the 'impl'
        // package
        Reflections reflections = new Reflections("com.dd2480.CMV.impl"); // Replace with your package
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