package com.gymflow.gymflow.helper;

public class validationHelper {
    /*
     * Recebe uma condição, que quando true, lança a exceção no segundo parâmetro
     */
    public void validate(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }
}
