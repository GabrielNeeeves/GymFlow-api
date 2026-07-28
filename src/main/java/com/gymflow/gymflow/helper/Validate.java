package com.gymflow.gymflow.helper;

public class Validate {
    /**
     * Receives a condition that, when true, throws the exception specified in the second parameter.
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }
}
