package com.tinubu.insurance.kata.application.rest;

import com.tinubu.insurance.kata.domain.model.EffectiveDate;
import com.tinubu.insurance.kata.domain.model.EndDate;
import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.model.PolicyStatus;
import com.tinubu.insurance.kata.domain.service.IInsurancePolicyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WebMvcTest(InsurancePolicyController.class)
class InsurancePolicyControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    private IInsurancePolicyService service;

    @Test
    void should_create_policy_when_call_post_insurance_policies_endpoint() throws Exception {
        // Arrange
        CreateInsurancePolicyRequest policyRequest = new CreateInsurancePolicyRequest("New Policy", "active", LocalDate.of(2024, 12, 7), LocalDate.of(2024, 12, 31));
        InsurancePolicy policy = InsurancePolicy.create("New Policy", PolicyStatus.ACTIVE, new EffectiveDate(LocalDate.of(2024, 12, 7)), new EndDate(LocalDate.of(2024, 12, 31)));
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
        InsurancePolicy policy = InsurancePolicy.create("New Policy", PolicyStatus.ACTIVE, new EffectiveDate(LocalDate.of(2024, 12, 7)), new EndDate(LocalDate.of(2024, 12, 31)));
        InsurancePolicyResponse expectedPolicyResponse = new InsurancePolicyResponse(policy.getInsurancePolicyId().id(),
                policy.getPolicyName(),
                policy.getPolicyStatus().name(),
                policy.getEffectiveDate().localDate(),
                policy.getEndDate().localDate(),
                policy.getCreationDate().creationDateTime(),
                policy.getUpdatedDate().updatedDateTime());

        when(service.getPolicyById(policy.getInsurancePolicyId())).thenReturn(Optional.of(policy));

        // Act & Assert
        mockMvc.perform(get("/api/insurance-policies/-415548445"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedPolicyResponse.toString(), JsonCompareMode.LENIENT));
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


}