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

    public static InsurancePolicy create(String policyName, PolicyStatus policyStatus, EffectiveDate effectiveDate, EndDate endDate) {
        if (policyName == null || policyName.isEmpty())
            throw new IllegalArgumentException("Policy name cannot be null or empty");
        if (policyStatus == null) throw new IllegalArgumentException("Policy status cannot be null");
        if (effectiveDate == null) throw new IllegalArgumentException("Effective date cannot be null");
        if (endDate == null) throw new IllegalArgumentException("End date cannot be null");
        return new InsurancePolicy(InsurancePolicyId.generate(), policyName, policyStatus, effectiveDate, endDate);
    }

    public InsurancePolicyId getInsurancePolicyId() {
        return insurancePolicyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public PolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public EffectiveDate getEffectiveDate() {
        return effectiveDate;
    }

    public EndDate getEndDate() {
        return endDate;
    }

    public CreationDate getCreationDate() {
        return creationDate;
    }

    public UpdatedDate getUpdatedDate() {
        return updatedDate;
    }

    public void setPolicyName(String updatedPolicy) {
        this.policyName = updatedPolicy;
    }

    public void setPolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }
}
