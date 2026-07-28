package com.gymflow.gymflow.plan.dto;

import com.gymflow.gymflow.plan.DurationUnit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreatePlanRequest(
        @NotBlank(message = "Name is required.")
        @Size(max = 100, message = "Name must have at most 100 characters.")
        String name,
        @NotNull()
        @Positive(message = "Price must be greater than zero.")
        BigDecimal price,
        @NotNull(message = "Duration is required.")
        @Positive(message = "Duration must be greater than zero.")
        Integer duration,
        @NotNull(message = "Duration unit is required.")
        DurationUnit durationUnit,
        @Size(max = 500, message = "Description must have at most 500 characters.")
        String description,
        boolean active
) {

}
