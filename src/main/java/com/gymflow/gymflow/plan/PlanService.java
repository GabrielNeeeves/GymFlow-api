package com.gymflow.gymflow.plan;

import com.gymflow.gymflow.helper.Validate;
import com.gymflow.gymflow.plan.dto.CreatePlanRequest;
import com.gymflow.gymflow.plan.dto.PlanResponse;
import com.gymflow.gymflow.plan.exceptions.PlanNotFoundException;
import com.gymflow.gymflow.plan.exceptions.PlanParameterInvalid;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<PlanResponse> getAllPlans() {
        return planRepository.findAll().stream().map(this::toResponse).toList();
    }

    public PlanResponse getPlanById(UUID planId) {
        PlanDomain currentPlan = planRepository.findById(planId)
                .orElseThrow(() -> new PlanNotFoundException(planId));

        return new PlanResponse(
                currentPlan.getName(),
                currentPlan.getPrice(),
                currentPlan.getDuration(),
                currentPlan.getDurationUnit(),
                currentPlan.getDescription(),
                currentPlan.isActive()
        );
    }

    public PlanResponse createPlan(CreatePlanRequest createPlanRequest) {
        Validate.throwIf(
                hasNullValue(createPlanRequest),
                new PlanParameterInvalid(createPlanRequest));

        return new PlanResponse(
                createPlanRequest.name(),
                createPlanRequest.price(),
                createPlanRequest.duration(),
                createPlanRequest.durationUnit(),
                createPlanRequest.description(),
                createPlanRequest.active()
        );
    }

    public void updatePlanById(UUID planId, CreatePlanRequest createPlanRequest) {
        PlanDomain currentPlan = planRepository.findById(planId)
                .orElseThrow(() -> new PlanNotFoundException(planId));

        currentPlan.updatePlan(
                createPlanRequest.name(),
                createPlanRequest.price(),
                createPlanRequest.duration(),
                createPlanRequest.durationUnit(),
                createPlanRequest.description(),
                createPlanRequest.active()
        );
        planRepository.save(currentPlan);
    }

    public void deletePlanById(UUID planId) {
        planRepository.findById(planId)
                .orElseThrow(() -> new PlanNotFoundException(planId));

        planRepository.deleteById(planId);
    }

    private boolean hasNullValue(Object... values) {
        return Arrays.stream(values).anyMatch(Objects::isNull);
    }

    private PlanResponse toResponse(PlanDomain planDomain) {
        return new PlanResponse(
                planDomain.getName(),
                planDomain.getPrice(),
                planDomain.getDuration(),
                planDomain.getDurationUnit(),
                planDomain.getDescription(),
                planDomain.isActive()
        );
    }

}
