package com.banquito.core.product.repository;

import com.banquito.core.product.model.InterestRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRateRepository extends JpaRepository<InterestRate, String> {
}
