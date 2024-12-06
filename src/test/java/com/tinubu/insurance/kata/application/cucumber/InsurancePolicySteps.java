package com.tinubu.insurance.kata.application.cucumber;

import com.tinubu.insurance.kata.domain.model.EffectiveDate;
import com.tinubu.insurance.kata.domain.model.EndDate;
import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.model.PolicyStatus;
import com.tinubu.insurance.kata.domain.persistance.InMemoryInsurancePolicyAdapter;
import com.tinubu.insurance.kata.domain.service.InsurancePolicyService;
import com.tinubu.insurance.kata.domain.spi.InsurancePolicyPersistencePort;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class InsurancePolicySteps {

    private InsurancePolicyService service;

    private InsurancePolicy currentPolicy;

    @Before
    public void setup() {
        InsurancePolicyPersistencePort persistencePort = new InMemoryInsurancePolicyAdapter();
        service = new InsurancePolicyService(persistencePort);
    }

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
        currentPolicy = service.createPolicy(currentPolicy);
        assertNotNull(currentPolicy);
        assertNotNull(currentPolicy.getInsurancePolicyId());
    }

    @Then("a unique id is generated")
    public void a_unique_id_is_generated() {
        assertNotNull(currentPolicy.getInsurancePolicyId().id());
    }

    @Given("A user want to update an existing insurance policy")
    public void a_user_want_to_update_an_existing_insurance_policy() {
        currentPolicy = InsurancePolicy.create("Existing Policy", PolicyStatus.ACTIVE, new EffectiveDate(LocalDate.of(2024, 12, 12)), new EndDate(LocalDate.of(2025, 1, 12)));
        currentPolicy = service.createPolicy(currentPolicy);
    }

    @When("The user update a policy")
    public void theUserUpdateAPolicy() {
        currentPolicy.setPolicyName("Updated Policy");
        currentPolicy.setPolicyStatus(PolicyStatus.INACTIVE);
    }

    @Then("The policy is updated")
    public void thePolicyIsUpdated() {
        currentPolicy = service.updatePolicy(currentPolicy);
        assertNotNull(currentPolicy);
        assertEquals("Updated Policy", currentPolicy.getPolicyName());
    }

    @Given("A user want to delete an existing insurance policy")
    public void a_user_want_to_delete_an_existing_insurance_policy() {
        currentPolicy = InsurancePolicy.create("Policy to delete", PolicyStatus.ACTIVE, new EffectiveDate(LocalDate.of(2024, 12, 12)), new EndDate(LocalDate.of(2025, 1, 12)));
        currentPolicy = service.createPolicy(currentPolicy);
    }

    @When("The user delete a policy")
    public void theUserDeleteAPolicy() {
        service.deletePolicy(currentPolicy.getInsurancePolicyId());
    }

    @Then("The policy is deleted")
    public void thePolicyIsDeleted() {
        assertFalse(service.getPolicyById(currentPolicy.getInsurancePolicyId()).isPresent());
    }
}
