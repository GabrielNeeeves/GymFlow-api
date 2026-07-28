package com.gymflow.gymflow.plan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanRepository extends JpaRepository<PlanDomain, UUID> {
}
