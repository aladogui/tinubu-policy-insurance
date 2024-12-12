package com.tinubu.insurance.kata.infrastructure.repository;

import com.tinubu.insurance.kata.domain.model.EffectiveDate;
import com.tinubu.insurance.kata.domain.model.EndDate;
import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.model.PolicyStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class InsurancePolicyEntity {

    @Id
    private Integer id;
    private String name;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;

    public InsurancePolicyEntity() {
    }

    public InsurancePolicyEntity(Integer id, String name, String status, LocalDate startDate, LocalDate endDate, LocalDateTime creationDate, LocalDateTime updatedDate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
    }

    public InsurancePolicyEntity(InsurancePolicy policy) {
        this.id = policy.getInsurancePolicyId().id();
        this.name = policy.getPolicyName();
        this.status = policy.getPolicyStatus().name();
        this.startDate = policy.getEffectiveDate().localDate();
        this.endDate = policy.getEndDate().localDate();
        this.creationDate = policy.getCreationDate().creationDateTime();
        this.updatedDate = policy.getUpdatedDate().updatedDateTime();
    }

    public InsurancePolicy toPolicy() {
        return InsurancePolicy.create(id, name, PolicyStatus.valueOf(status), new EffectiveDate(startDate), new EndDate(endDate), creationDate, updatedDate);
    }
}
