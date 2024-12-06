package com.tinubu.insurance.kata.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InsurancePolicyTest {

    private final EffectiveDate effectiveDate = new EffectiveDate(LocalDate.of(2024, 12, 12));
    private final EndDate endDate = new EndDate(LocalDate.of(2025, 1, 12));


    @Test
    void should_create_a_policy_when_all_attributes_are_valid() {

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

    @Test
    void should_throw_exception_when_policy_name_is_null() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InsurancePolicy.create(null, PolicyStatus.ACTIVE, effectiveDate, endDate);
        });

        assertEquals("Policy name cannot be null or empty", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_policy_name_is_empty() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InsurancePolicy.create("", PolicyStatus.ACTIVE, effectiveDate, endDate);
        });

        assertEquals("Policy name cannot be null or empty", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_policy_status_is_null() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InsurancePolicy.create("Test Policy", null, effectiveDate, endDate);
        });

        assertEquals("Policy status cannot be null", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_effective_date_is_null() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InsurancePolicy.create("Test Policy", PolicyStatus.ACTIVE, null, endDate);
        });

        assertEquals("Effective date cannot be null", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_end_date_is_null() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InsurancePolicy.create("Test Policy", PolicyStatus.ACTIVE, effectiveDate, null);
        });

        assertEquals("End date cannot be null", exception.getMessage());
    }


}
