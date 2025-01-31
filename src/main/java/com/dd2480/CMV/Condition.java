package com.dd2480.CMV;

/**
 * Interface for each LIC.
 */
public interface Condition {
    /**
     * Evaluates the condition based on the provided context.
     *
     * @param context the context in which the condition is evaluated
     * @return true if the condition is met, false otherwise
     */
    boolean evaluate(ConditionContext context);
}