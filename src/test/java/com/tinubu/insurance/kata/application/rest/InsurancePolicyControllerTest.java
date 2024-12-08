package com.tinubu.insurance.kata.application.rest;

import com.tinubu.insurance.kata.domain.model.*;
import com.tinubu.insurance.kata.domain.service.IInsurancePolicyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WebMvcTest(InsurancePolicyController.class)
class InsurancePolicyControllerTest {

    private final InsurancePolicy policy = InsurancePolicy.create("New Policy", PolicyStatus.ACTIVE, new EffectiveDate(LocalDate.of(2024, 12, 7)), new EndDate(LocalDate.of(2024, 12, 31)));
    private final InsurancePolicyRequest policyRequest = new InsurancePolicyRequest("New Policy", "active", LocalDate.of(2024, 12, 7), LocalDate.of(2024, 12, 31));
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    private IInsurancePolicyService service;

    @Test
    void should_create_policy_when_call_post_insurance_policies_endpoint() throws Exception {
        // Arrange
        when(service.createPolicy(any(InsurancePolicy.class))).thenReturn(policy);

        // Act & Assert
        mockMvc.perform(post("/api/insurance-policies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(policyRequest.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpectAll(jsonPath("$.id").value(policy.getInsurancePolicyId().id()),
                        jsonPath("$.name").value("New Policy"),
                        jsonPath("$.status").value("ACTIVE"),
                        jsonPath("$.startDate").value("2024-12-07"),
                        jsonPath("$.endDate").value("2024-12-31"));

    }

    @Test
    void should_return_an_existing_policy_when_call_get_insurance_policies_endpoint() throws Exception {
        // Arrange
        InsurancePolicyResponse expectedPolicyResponse = new InsurancePolicyResponse(policy.getInsurancePolicyId().id(),
                policy.getPolicyName(),
                policy.getPolicyStatus().name(),
                policy.getEffectiveDate().localDate(),
                policy.getEndDate().localDate(),
                policy.getCreationDate().creationDateTime(),
                policy.getUpdatedDate().updatedDateTime());

        when(service.getPolicyById(any(InsurancePolicyId.class))).thenReturn(Optional.of(policy));

        // Act & Assert
        mockMvc.perform(get("/api/insurance-policies/-415548445"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpectAll(jsonPath("$.id").value(policy.getInsurancePolicyId().id()),
                        jsonPath("$.name").value(expectedPolicyResponse.name()),
                        jsonPath("$.status").value(expectedPolicyResponse.status()),
                        jsonPath("$.startDate").value(expectedPolicyResponse.startDate().toString()),
                        jsonPath("$.endDate").value(expectedPolicyResponse.endDate().toString()),
                        jsonPath("$.creationDate").exists(),
                        jsonPath("$.updatedDate").exists());

    }

    @Test
    void should_return_404_when_call_get_insurance_policies_endpoint_with_an_invalid_id() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/insurance-policies/"))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_400_when_call_get_insurance_policies_endpoint_with_an_invalid_id() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/insurance-policies/null"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_all_policies_when_call_get_insurance_policies_endpoint() throws Exception {
        // Arrange
        InsurancePolicy policy1 = InsurancePolicy.create("Policy 1", PolicyStatus.ACTIVE, new EffectiveDate(LocalDate.of(2024, 12, 7)), new EndDate(LocalDate.of(2024, 12, 31)));
        InsurancePolicy policy2 = InsurancePolicy.create("Policy 2", PolicyStatus.ACTIVE, new EffectiveDate(LocalDate.of(2024, 12, 7)), new EndDate(LocalDate.of(2024, 12, 31)));
        when(service.getAllPolicies()).thenReturn(List.of(policy1, policy2));
        // Act & Assert
        mockMvc.perform(get("/api/insurance-policies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void should_return_updated_policy_when_call_put_insurance_policies_endpoint() throws Exception {
        // Arrange
        InsurancePolicyRequest updatedPolicyRequest = new InsurancePolicyRequest("Updated Policy", "INACTIVE", LocalDate.of(2024, 2, 7), LocalDate.of(2024, 2, 28));
        when(service.getPolicyById(any(InsurancePolicyId.class))).thenReturn(Optional.of(policy));
        policy.setPolicyStatus(PolicyStatus.INACTIVE);
        policy.setPolicyName("Updated Policy");
        policy.setEffectiveDate(new EffectiveDate(LocalDate.of(2024, 2, 7)));
        policy.setEndDate(new EndDate(LocalDate.of(2024, 2, 28)));
        when(service.updatePolicy(any(InsurancePolicy.class))).thenReturn(policy);

        // Act & Assert
        mockMvc.perform(put("/api/insurance-policies/" + policy.getInsurancePolicyId().id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPolicyRequest.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(policy.getInsurancePolicyId().id()))
                .andExpect(jsonPath("$.name").value("Updated Policy"))
                .andExpect(jsonPath("$.status").value("INACTIVE"))
                .andExpect(jsonPath("$.startDate").value("2024-02-07"))
                .andExpect(jsonPath("$.endDate").value("2024-02-28"));
    }


}