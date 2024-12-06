package com.tinubu.insurance.kata.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InsurancePolicyIdTest {

    @Test
    void should_generate_non_null_id() {
        InsurancePolicyId id = InsurancePolicyId.generate();
        assertNotNull(id);
    }

    @Test
    void should_generate_unique_ids() {
        InsurancePolicyId id1 = InsurancePolicyId.generate();
        InsurancePolicyId id2 = InsurancePolicyId.generate();
        assertNotEquals(id1, id2);
    }
}
