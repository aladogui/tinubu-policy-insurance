package com.tinubu.insurance.kata.domain.model;


import java.time.LocalDateTime;

public class InsurancePolicy {

    private final InsurancePolicyId insurancePolicyId;
    private final CreationDate creationDate;
    private final UpdatedDate updatedDate;
    private String policyName;
    private PolicyStatus policyStatus;
    private EffectiveDate effectiveDate;
    private EndDate endDate;

    private InsurancePolicy(InsurancePolicyId insurancePolicyId, String policyName, PolicyStatus policyStatus, EffectiveDate effectiveDate, EndDate endDate) {
        this.insurancePolicyId = insurancePolicyId;
        this.policyName = policyName;
        this.policyStatus = policyStatus;
        this.effectiveDate = effectiveDate;
        this.endDate = endDate;
        this.creationDate = new CreationDate(LocalDateTime.now());
        this.updatedDate = new UpdatedDate(LocalDateTime.now());
    }

    public InsurancePolicy(InsurancePolicyId insurancePolicyId, CreationDate creationDate, UpdatedDate updatedDate, String policyName, PolicyStatus policyStatus, EffectiveDate effectiveDate, EndDate endDate) {
        this.insurancePolicyId = insurancePolicyId;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
        this.policyName = policyName;
        this.policyStatus = policyStatus;
        this.effectiveDate = effectiveDate;
        this.endDate = endDate;
    }

    public static InsurancePolicy create(String policyName, PolicyStatus policyStatus, EffectiveDate effectiveDate, EndDate endDate) {
        if (policyName == null || policyName.isEmpty())
            throw new IllegalArgumentException("Policy name cannot be null or empty");
        if (policyStatus == null) throw new IllegalArgumentException("Policy status cannot be null");
        if (effectiveDate == null) throw new IllegalArgumentException("Effective date cannot be null");
        if (endDate == null) throw new IllegalArgumentException("End date cannot be null");
        return new InsurancePolicy(InsurancePolicyId.generate(), policyName, policyStatus, effectiveDate, endDate);
    }

    public static InsurancePolicy create(Integer id, String name, PolicyStatus policyStatus, EffectiveDate effectiveDate, EndDate endDate, LocalDateTime creationDate, LocalDateTime updatedDate) {
        return new InsurancePolicy(new InsurancePolicyId(id), new CreationDate(creationDate), new UpdatedDate(updatedDate), name, policyStatus, effectiveDate, endDate);
    }

    public InsurancePolicyId getInsurancePolicyId() {
        return insurancePolicyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String updatedPolicy) {
        this.policyName = updatedPolicy;
    }

    public PolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }

    public EffectiveDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(EffectiveDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public EndDate getEndDate() {
        return endDate;
    }

    public void setEndDate(EndDate endDate) {
        this.endDate = endDate;
    }

    public CreationDate getCreationDate() {
        return creationDate;
    }

    public UpdatedDate getUpdatedDate() {
        return updatedDate;
    }
}
