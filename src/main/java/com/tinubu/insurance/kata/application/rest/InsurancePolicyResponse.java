package com.tinubu.insurance.kata.application.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record InsurancePolicyResponse(Integer id, String name, String status, LocalDate startDate,
                                      LocalDate endDate, LocalDateTime creationDate,
                                      LocalDateTime updatedDate) {

    public String toString() {
        return "{" +
                "\"id\":" + id + ',' +
                "\"name\":\"" + name + '\"' +
                ", \"status\":\"" + status + '\"' +
                ", \"startDate\":\"" + startDate + '\"' +
                ", \"endDate\":\"" + endDate + '\"' +
                ", \"creationDate\":\"" + creationDate + '\"' +
                ", \"updatedDate\":\"" + updatedDate + '\"' +
                '}';
    }


}
