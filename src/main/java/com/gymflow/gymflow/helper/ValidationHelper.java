package com.gymflow.gymflow.helper;

public class ValidationHelper {
    /*
     * Recebe uma condição, que quando true, lança a exceção no segundo parâmetro
     */
    public static void validate(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }
}
