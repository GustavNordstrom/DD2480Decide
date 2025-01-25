package com.dd2480.CMV.impl;

import com.dd2480.CMV.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Set;

public class ConditionManagerImplTest {
    @Test
    public void testClassScanAndInstantiate() {
        // Use Reflections to scan for classes implementing Condition in the 'impl' package
        Reflections reflections = new Reflections("com.dd2480.CMV.impl");  // Replace with your package
        Set<Class<? extends Condition>> conditionClasses = reflections.getSubTypesOf(Condition.class);

        Assertions.assertNotEquals(conditionClasses.size(), 0);
        // Iterate over each class and instantiate it using reflection
        for (Class<? extends Condition> conditionClass : conditionClasses) {
            try {
                // Instantiate the condition class using the default constructor
                Constructor<? extends Condition> constructor = conditionClass.getConstructor();
                Condition condition = constructor.newInstance();

                // Now you have the condition instance, and you can use it
                System.out.println("Instantiated condition: " + condition.getClass().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
