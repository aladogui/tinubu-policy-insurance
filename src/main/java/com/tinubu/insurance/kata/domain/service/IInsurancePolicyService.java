package com.tinubu.insurance.kata.domain.service;

import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.model.InsurancePolicyId;

import java.util.List;
import java.util.Optional;

public interface IInsurancePolicyService {
    InsurancePolicy createPolicy(InsurancePolicy policy);

    Optional<InsurancePolicy> getPolicyById(InsurancePolicyId id);

    InsurancePolicy updatePolicy(InsurancePolicy currentPolicy);

    void deletePolicy(InsurancePolicyId insurancePolicyId);

    List<InsurancePolicy> getAllPolicies();
}
