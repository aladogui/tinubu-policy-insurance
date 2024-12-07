package com.tinubu.insurance.kata.domain.spi;

import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.model.InsurancePolicyId;

import java.util.List;
import java.util.Optional;

public interface InsurancePolicyPersistencePort {
    InsurancePolicy save(InsurancePolicy policy);

    Optional<InsurancePolicy> findById(InsurancePolicyId id);

    void deleteById(InsurancePolicyId insurancePolicyId);

    List<InsurancePolicy> findAll();
}
