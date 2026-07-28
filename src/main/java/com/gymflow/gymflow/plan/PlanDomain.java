package com.gymflow.gymflow.plan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "plan")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDomain {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer duration;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private DurationUnit durationUnit;
    @Column(length = 500)
    private String description;
    @Column(nullable = false)
    private boolean active;

    public PlanDomain(String name, BigDecimal price, Integer duration,
                      DurationUnit durationUnit, String description, boolean active) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.durationUnit = durationUnit;
        this.description = description;
        this.active = active;
    }

    static PlanDomain createPlan(String name, BigDecimal price, Integer duration,
                      DurationUnit durationUnit,String description, boolean active) {
        return new PlanDomain(name, price, duration, durationUnit, description, active);
    }

    void updatePlan(String name, BigDecimal price, Integer duration, DurationUnit durationUnit, String description, boolean active) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.durationUnit = durationUnit;
        this.description = description;
        this.active = active;
    }
}
