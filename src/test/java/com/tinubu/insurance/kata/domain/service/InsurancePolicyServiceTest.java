package com.tinubu.insurance.kata.domain.service;

import com.tinubu.insurance.kata.domain.model.EffectiveDate;
import com.tinubu.insurance.kata.domain.model.EndDate;
import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.model.PolicyStatus;
import com.tinubu.insurance.kata.domain.persistance.InMemoryInsurancePolicyAdapter;
import com.tinubu.insurance.kata.domain.spi.InsurancePolicyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InsurancePolicyServiceTest {


    private final EffectiveDate effectiveDate = new EffectiveDate(LocalDate.now());
    private final EndDate endDate = new EndDate(LocalDate.now().plusYears(1));
    InsurancePolicy policy;
    private InsurancePolicyPersistencePort repository;
    private InsurancePolicyService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryInsurancePolicyAdapter();
        service = new InsurancePolicyService(repository);
        policy = InsurancePolicy.create("Test Policy", PolicyStatus.ACTIVE, effectiveDate, endDate);
    }

    @Test
    void should_create_and_persist_policy_when_policy_is_valid() {
        InsurancePolicy savedPolicy = service.createPolicy(policy);

        assertNotNull(savedPolicy);
    }

    @Test
    void should_update_policy_when_values_changed() {
        // Arrange
        InsurancePolicy savedPolicy = service.createPolicy(policy);
        savedPolicy.setPolicyName("Updated Policy");
        savedPolicy.setPolicyStatus(PolicyStatus.INACTIVE);

        // Act
        InsurancePolicy result = service.updatePolicy(savedPolicy);

        // Assert
        assertEquals("Updated Policy", result.getPolicyName());
        assertEquals(PolicyStatus.INACTIVE, result.getPolicyStatus());
    }

}
