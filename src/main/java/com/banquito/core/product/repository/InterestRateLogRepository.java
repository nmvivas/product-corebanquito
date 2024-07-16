package com.banquito.core.product.repository;

import com.banquito.core.product.model.InterestRateLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRateLogRepository extends JpaRepository<InterestRateLog, Integer> {
}
