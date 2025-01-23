package com.dd2480.common;

public enum Connector {
    NOTUSED(777),
    ORR(778), // Automatically increments after NOTUSED if desired
    ANDD(779);

    private final int value;

    // Constructor to assign value to enum constants
    Connector(int value) {
        this.value = value;
    }

    // Getter to retrieve the value of the enum constant
    public int getValue() {
        return value;
    }

    // Optionally, you can override toString() to print the value as desired
    @Override
    public String toString() {
        return name() + "(" + value + ")";
    }

    // You can create a static method to retrieve an enum by its value, if needed
    public static Connector fromValue(int value) {
        for (Connector connector : values()) {
            if (connector.getValue() == value) {
                return connector;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}