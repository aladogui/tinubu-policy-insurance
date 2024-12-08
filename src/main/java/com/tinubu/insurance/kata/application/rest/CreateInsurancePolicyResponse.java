package com.tinubu.insurance.kata.application.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateInsurancePolicyResponse(Integer id, String name, String status, LocalDate startDate,
                                            LocalDate endDate, LocalDateTime creationDate, LocalDateTime updatedDate) {
}
