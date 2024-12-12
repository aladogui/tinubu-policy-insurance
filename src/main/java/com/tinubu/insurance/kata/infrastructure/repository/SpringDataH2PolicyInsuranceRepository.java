package com.tinubu.insurance.kata.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataH2PolicyInsuranceRepository extends JpaRepository<InsurancePolicyEntity, Integer> {
}
