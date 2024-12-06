package com.tinubu.insurance.kata.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InsurancePolicyTest {


    @Test
    void should_create_a_policy_when_all_attributes_are_valid() {
        EffectiveDate effectiveDate = new EffectiveDate(LocalDate.of(2024, 12, 12));
        EndDate endDate = new EndDate(LocalDate.of(2025, 1, 12));
        InsurancePolicy policy = InsurancePolicy.create("Test Policy", PolicyStatus.ACTIVE, effectiveDate, endDate);

        assertNotNull(policy);
        assertEquals("Test Policy", policy.getPolicyName());
        assertEquals(PolicyStatus.ACTIVE, policy.getPolicyStatus());
        assertEquals(effectiveDate, policy.getEffectiveDate());
        assertEquals(endDate, policy.getEndDate());
        assertNotNull(policy.getCreationDate());
        assertNotNull(policy.getUpdatedDate());
        assertNotNull(policy.getInsurancePolicyId());
    }


}
