package com.gymflow.gymflow.plan.exceptions;

import java.util.UUID;

public class PlanNotFoundException extends RuntimeException {

    public PlanNotFoundException(UUID planId) {
        super("Plan with id: " + planId + " not found");
    }

}
