package com.gymflow.gymflow.plan;

import com.gymflow.gymflow.plan.dto.CreatePlanRequest;
import com.gymflow.gymflow.plan.dto.PlanResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/plan")
public class PlanController {

    private final PlanService planService;

    PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public ResponseEntity<List<PlanResponse>> getAllPlans() {
        return ResponseEntity.ok(planService.getAllPlans());
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanResponse> getPlanById(@PathVariable UUID planId) {
        return ResponseEntity.ok(planService.getPlanById(planId));
    }

    @PostMapping("/create-plan")
    public ResponseEntity<PlanResponse> createPlan(@RequestBody CreatePlanRequest createPlanRequest) {
        PlanResponse planResponse = planService.createPlan(createPlanRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(planResponse);
    }

    @PutMapping("/{planId}")
    public void updatePlanById(@PathVariable UUID planId, @RequestBody CreatePlanRequest createPlanRequest) {
        planService.updatePlanById(planId, createPlanRequest);
    }

    @DeleteMapping("/{planId}")
    public void deletePlanById(@PathVariable UUID planId) {
        planService.deletePlanById(planId);
    }
}
