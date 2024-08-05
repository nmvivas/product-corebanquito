package com.banquito.core.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.product.model.InterestRateLog;

public interface InterestRateLogRepository extends JpaRepository<InterestRateLog, Integer> {
    Optional<InterestRateLog> findByUniqueId(String uniqueId);
    void deleteByUniqueId(String uniqueId);
}
