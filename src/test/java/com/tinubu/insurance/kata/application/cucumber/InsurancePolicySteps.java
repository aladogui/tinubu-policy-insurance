package com.tinubu.insurance.kata.application.cucumber;

import com.tinubu.insurance.kata.domain.model.EffectiveDate;
import com.tinubu.insurance.kata.domain.model.EndDate;
import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.model.PolicyStatus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;

public class InsurancePolicySteps {

    private InsurancePolicy currentPolicy;

    @Given("A user want to create a new insurance policy")
    public void a_user_want_to_create_a_new_insurance_policy() {
        // do nothing
    }

    @When("The user create a policy")
    public void user_create_a_policy() {
        EffectiveDate effectiveDate = new EffectiveDate(LocalDate.of(2024, 12, 12));
        EndDate endDate = new EndDate(LocalDate.of(2025, 1, 12));
        currentPolicy = InsurancePolicy.create("PolicyName", PolicyStatus.ACTIVE, effectiveDate, endDate);
    }

    @Then("The policy is created")
    public void the_policy_is_created() {
    }

    @Then("a unique id is generated")
    public void a_unique_id_is_generated() {
    }
}