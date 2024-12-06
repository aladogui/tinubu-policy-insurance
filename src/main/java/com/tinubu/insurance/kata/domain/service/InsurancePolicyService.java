package com.tinubu.insurance.kata.domain.service;

import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.model.InsurancePolicyId;
import com.tinubu.insurance.kata.domain.spi.InsurancePolicyPersistencePort;

import java.util.Optional;


public class InsurancePolicyService {

    InsurancePolicyPersistencePort repository;

    public InsurancePolicyService(InsurancePolicyPersistencePort persistencePort) {
        this.repository = persistencePort;
    }

    public InsurancePolicy createPolicy(InsurancePolicy policy) {
        return repository.save(policy);
    }

    public Optional<InsurancePolicy> getPolicyById(InsurancePolicyId id) {
        return repository.findById(id);
    }

    public InsurancePolicy updatePolicy(InsurancePolicy currentPolicy) {
        repository.findById(currentPolicy.getInsurancePolicyId())
                .orElseThrow(() -> new IllegalArgumentException("Policy not found"));
        return repository.save(currentPolicy);
    }

    public void deletePolicy(InsurancePolicyId insurancePolicyId) {
        repository.deleteById(insurancePolicyId);
    }
}
