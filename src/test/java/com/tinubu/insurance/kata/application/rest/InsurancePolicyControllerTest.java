package com.tinubu.insurance.kata.application.rest;

import com.tinubu.insurance.kata.domain.model.EffectiveDate;
import com.tinubu.insurance.kata.domain.model.EndDate;
import com.tinubu.insurance.kata.domain.model.InsurancePolicy;
import com.tinubu.insurance.kata.domain.model.PolicyStatus;
import com.tinubu.insurance.kata.domain.service.IInsurancePolicyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(InsurancePolicyController.class)
class InsurancePolicyControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Mock
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
                .andExpect(jsonPath("$.name").value(policyRequest.getName()));
    }


}