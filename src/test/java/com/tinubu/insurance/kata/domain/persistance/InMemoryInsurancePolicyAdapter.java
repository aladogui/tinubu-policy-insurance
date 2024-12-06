package com.tinubu.insurance.kata.domain.persistance;

import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.model.InsurancePolicyId;
import com.tinubu.insurance.kata.domain.spi.InsurancePolicyPersistencePort;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryInsurancePolicyAdapter implements InsurancePolicyPersistencePort {

    private final Map<InsurancePolicyId, InsurancePolicy> policies = new HashMap<>();

    @Override
    public InsurancePolicy save(InsurancePolicy policy) {
        if (policy.getInsurancePolicyId() == null) {
            policy = InsurancePolicy.create(policy.getPolicyName(), policy.getPolicyStatus(), policy.getEffectiveDate(), policy.getEndDate());
        }

        policies.put(policy.getInsurancePolicyId(), policy);
        return policy;
    }

    @Override
    public Optional<InsurancePolicy> findById(InsurancePolicyId id) {
        return Optional.ofNullable(policies.get(id));
    }


}
