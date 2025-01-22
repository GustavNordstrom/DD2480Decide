package com.dd2480.example;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    public void testAdd() {
        int res = Calculator.add(1, 2);
        assertEquals(res, 3);
    }
}
