package com.gymflow.gymflow.plan.exceptions;

public class PlanParameterInvalid extends RuntimeException {

    public PlanParameterInvalid(Object object) {
        super(object + " is invalid");
    }
}
