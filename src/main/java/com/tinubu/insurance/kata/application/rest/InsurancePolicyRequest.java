package com.tinubu.insurance.kata.application.rest;


import jakarta.annotation.Nonnull;

import java.time.LocalDate;

public record InsurancePolicyRequest(String name, String status, LocalDate startDate, LocalDate endDate) {

    public InsurancePolicyRequest(@Nonnull String name, @Nonnull String status, @Nonnull LocalDate startDate, @Nonnull LocalDate endDate) {
        if (name.isEmpty()) throw new IllegalArgumentException("Policy name cannot be empty");
        if (status.isEmpty()) throw new IllegalArgumentException("Policy status cannot be empty");
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toString() {
        return "{" +
                "\"name\":\"" + name + '\"' +
                ", \"status\":\"" + status + '\"' +
                ", \"startDate\":\"" + startDate + '\"' +
                ", \"endDate\":\"" + endDate.toString() + '\"' +
                '}';
    }

}
