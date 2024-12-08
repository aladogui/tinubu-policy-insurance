package com.tinubu.insurance.kata.application.rest;

import com.tinubu.insurance.kata.domain.model.*;
import com.tinubu.insurance.kata.domain.service.IInsurancePolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance-policies")
public class InsurancePolicyController {

    private final IInsurancePolicyService service;

    public InsurancePolicyController(IInsurancePolicyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InsurancePolicyResponse> createPolicy(@RequestBody InsurancePolicyRequest policyToCreate) {
        InsurancePolicy createdPolicy = service.createPolicy(mapToInsurancePolicy(policyToCreate));
        return ResponseEntity.ok(mapToPolicyResponse(createdPolicy));
    }


    @GetMapping("/{id}")
    public ResponseEntity<InsurancePolicyResponse> getPolicy(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        return service.getPolicyById(new InsurancePolicyId(id))
                .map(policy -> ResponseEntity.ok(mapToPolicyResponse(policy)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<InsurancePolicyResponse>> getAllPolicies() {
        return ResponseEntity.ok(mapToPoliciesResponse(service.getAllPolicies()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsurancePolicyResponse> updatePolicy(@PathVariable Integer id, @RequestBody InsurancePolicyRequest policyRequest) {
        InsurancePolicy existingPolicy = service.getPolicyById(new InsurancePolicyId(id)).orElseThrow(() -> new IllegalArgumentException("Policy not found"));
        existingPolicy.setPolicyName(policyRequest.name());
        existingPolicy.setPolicyStatus(PolicyStatus.valueOf(policyRequest.status().toUpperCase()));
        existingPolicy.setEffectiveDate(new EffectiveDate(policyRequest.startDate()));
        existingPolicy.setEndDate(new EndDate(policyRequest.endDate()));
        return ResponseEntity.ok(mapToPolicyResponse(service.updatePolicy(existingPolicy)));
    }

    private List<InsurancePolicyResponse> mapToPoliciesResponse(List<InsurancePolicy> allPolicies) {
        return allPolicies.stream().map(this::mapToPolicyResponse).toList();
    }

    private InsurancePolicyResponse mapToPolicyResponse(InsurancePolicy createdPolicy) {
        return new InsurancePolicyResponse(createdPolicy.getInsurancePolicyId().id(),
                createdPolicy.getPolicyName(),
                createdPolicy.getPolicyStatus().name(),
                createdPolicy.getEffectiveDate().localDate(),
                createdPolicy.getEndDate().localDate(),
                createdPolicy.getCreationDate().creationDateTime(),
                createdPolicy.getUpdatedDate().updatedDateTime());
    }

    private InsurancePolicy mapToInsurancePolicy(InsurancePolicyRequest request) {
        return InsurancePolicy.create(request.name(), PolicyStatus.valueOf(request.status().toUpperCase()), new EffectiveDate(request.startDate()), new EndDate(request.endDate()));
    }

}
