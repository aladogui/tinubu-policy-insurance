package com.tinubu.insurance.kata.domain.model;


import java.util.UUID;

public record InsurancePolicyId(Integer id) {
    public static InsurancePolicyId generate() {
        return new InsurancePolicyId(UUID.randomUUID().hashCode());
    }

}
