package com.tinubu.insurance.kata.domain.spi;

import com.tinubu.insurance.kata.domain.model.InsurancePolicy;

public interface InsurancePolicyPersistencePort {
    InsurancePolicy save(InsurancePolicy policy);

}
