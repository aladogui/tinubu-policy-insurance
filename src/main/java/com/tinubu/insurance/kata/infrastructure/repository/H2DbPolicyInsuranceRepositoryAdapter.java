package com.tinubu.insurance.kata.infrastructure.repository;

import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.model.InsurancePolicyId;
import com.tinubu.insurance.kata.domain.spi.InsurancePolicyPersistencePort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class H2DbPolicyInsuranceRepositoryAdapter implements InsurancePolicyPersistencePort {

    private final SpringDataH2PolicyInsuranceRepository policyInsuranceRepository;


    public H2DbPolicyInsuranceRepositoryAdapter(SpringDataH2PolicyInsuranceRepository repository) {
        this.policyInsuranceRepository = repository;
    }

    @Override
    public InsurancePolicy save(InsurancePolicy policy) {

        return policyInsuranceRepository.save(new InsurancePolicyEntity(policy)).toPolicy();
    }

    @Override
    public Optional<InsurancePolicy> findById(InsurancePolicyId id) {
        return policyInsuranceRepository.findById(id.id()).map(InsurancePolicyEntity::toPolicy);
    }

    @Override
    public void deleteById(InsurancePolicyId id) {

    }

    @Override
    public List<InsurancePolicy> findAll() {
        return policyInsuranceRepository.findAll().stream().map(InsurancePolicyEntity::toPolicy).toList();
    }

}

