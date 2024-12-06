package com.tinubu.insurance.kata.domain.service;

import com.tinubu.insurance.kata.domain.model.InsurancePolicy;

public interface IInsurancePolicyService {
    InsurancePolicy createPolicy(InsurancePolicy currentPolicy);
}
