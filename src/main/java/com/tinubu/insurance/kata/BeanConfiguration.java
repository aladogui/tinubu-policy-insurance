package com.tinubu.insurance.kata;

import com.tinubu.insurance.kata.domain.service.IInsurancePolicyService;
import com.tinubu.insurance.kata.domain.service.InsurancePolicyService;
import com.tinubu.insurance.kata.domain.spi.InsurancePolicyPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    IInsurancePolicyService insurancePolicyService(final InsurancePolicyPersistencePort insurancePolicyPersistencePort) {
        return new InsurancePolicyService(insurancePolicyPersistencePort);
    }
}