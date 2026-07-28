package com.gymflow.gymflow.plan.dto;

import com.gymflow.gymflow.plan.DurationUnit;

import java.math.BigDecimal;

public record PlanResponse(
        String name,
        BigDecimal price,
        Integer duration,
        DurationUnit durationUnit,
        String description,
        boolean active
) {
}
