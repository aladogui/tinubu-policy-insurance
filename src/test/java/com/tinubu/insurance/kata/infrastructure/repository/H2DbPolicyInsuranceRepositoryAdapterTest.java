package com.tinubu.insurance.kata.infrastructure.repository;

import com.tinubu.insurance.kata.domain.model.EffectiveDate;
import com.tinubu.insurance.kata.domain.model.EndDate;
import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.model.PolicyStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig
@SpringBootTest
public class H2DbPolicyInsuranceRepositoryAdapterTest {


    @Autowired
    private H2DbPolicyInsuranceRepositoryAdapter adapter;


    @Test
    void should_persist_policy_when_call_save_methode() {
        // Arrange
        InsurancePolicy policy = InsurancePolicy.create("Policy 1", PolicyStatus.INACTIVE, new EffectiveDate(LocalDate.of(2024, 12, 7)), new EndDate(LocalDate.of(2024, 12, 31)));

        // Act
        InsurancePolicy savedPolicy = adapter.save(policy);

        // Assert
        assertNotNull(savedPolicy.getInsurancePolicyId());
        assertNotNull(savedPolicy.getInsurancePolicyId().id());

        assertTrue(adapter.findById(savedPolicy.getInsurancePolicyId()).isPresent());
    }

}
