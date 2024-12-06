package com.tinubu.insurance.kata.domain.service;

import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.spi.InsurancePolicyPersistencePort;


public class InsurancePolicyService {

    InsurancePolicyPersistencePort repository;

    public InsurancePolicyService(InsurancePolicyPersistencePort persistencePort) {
        this.repository = persistencePort;
    }

    public InsurancePolicy createPolicy(InsurancePolicy policy) {
        return repository.save(policy);
    }

    public InsurancePolicy updatePolicy(InsurancePolicy currentPolicy) {
        return repository.save(currentPolicy);
    }
}
