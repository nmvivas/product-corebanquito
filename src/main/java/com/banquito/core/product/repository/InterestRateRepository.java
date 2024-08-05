package com.banquito.core.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.product.model.InterestRate;

public interface InterestRateRepository extends JpaRepository<InterestRate, String> {
    Optional<InterestRate> findByUniqueId(String uniqueId);
    void deleteByUniqueId(String uniqueId);
}
