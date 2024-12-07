package com.tinubu.insurance.kata.domain.service;

import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.model.InsurancePolicyId;
import com.tinubu.insurance.kata.domain.spi.InsurancePolicyPersistencePort;

import java.util.List;
import java.util.Optional;


public class InsurancePolicyService implements IInsurancePolicyService {

    InsurancePolicyPersistencePort repository;

    public InsurancePolicyService(InsurancePolicyPersistencePort persistencePort) {
        this.repository = persistencePort;
    }

    @Override
    public InsurancePolicy createPolicy(InsurancePolicy policy) {
        return repository.save(policy);
    }

    @Override
    public Optional<InsurancePolicy> getPolicyById(InsurancePolicyId id) {
        return repository.findById(id);
    }

    @Override
    public InsurancePolicy updatePolicy(InsurancePolicy currentPolicy) {
        repository.findById(currentPolicy.getInsurancePolicyId())
                .orElseThrow(() -> new IllegalArgumentException("Policy not found"));
        return repository.save(currentPolicy);
    }

    @Override
    public void deletePolicy(InsurancePolicyId insurancePolicyId) {
        repository.deleteById(insurancePolicyId);
    }

    @Override
    public List<InsurancePolicy> getAllPolicies() {
        return repository.findAll();
    }
}
