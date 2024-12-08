package com.tinubu.insurance.kata.application.rest;

import com.tinubu.insurance.kata.domain.model.EffectiveDate;
import com.tinubu.insurance.kata.domain.model.EndDate;
import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.model.PolicyStatus;
import com.tinubu.insurance.kata.domain.service.IInsurancePolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/insurance-policies")
public class InsurancePolicyController {

    private final IInsurancePolicyService service;

    public InsurancePolicyController(IInsurancePolicyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreateInsurancePolicyResponse> createPolicy(@RequestBody CreateInsurancePolicyRequest policyToCreate) {
        InsurancePolicy createdPolicy = service.createPolicy(mapToInsurancePolicy(policyToCreate));
        return ResponseEntity.ok(mapToCreateResponse(createdPolicy));
    }

    private CreateInsurancePolicyResponse mapToCreateResponse(InsurancePolicy createdPolicy) {
        return new CreateInsurancePolicyResponse(createdPolicy.getInsurancePolicyId().id(),
                createdPolicy.getPolicyName(),
                createdPolicy.getPolicyStatus().name(),
                createdPolicy.getEffectiveDate().localDate(),
                createdPolicy.getEndDate().localDate(),
                createdPolicy.getCreationDate().creationDateTime(),
                createdPolicy.getUpdatedDate().updatedDateTime());
    }

    private InsurancePolicy mapToInsurancePolicy(CreateInsurancePolicyRequest request) {
        return InsurancePolicy.create(request.name(), PolicyStatus.valueOf(request.status().toUpperCase()), new EffectiveDate(request.startDate()), new EndDate(request.endDate()));
    }

}
