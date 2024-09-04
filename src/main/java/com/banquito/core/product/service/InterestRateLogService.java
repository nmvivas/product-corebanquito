package com.banquito.core.product.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.banquito.core.product.model.InterestRateLog;
import com.banquito.core.product.repository.InterestRateLogRepository;
import com.banquito.core.product.util.UniqueIdGeneration;

@Service
public class InterestRateLogService {

    private final InterestRateLogRepository interestRateLogRepository;
    private final UniqueIdGeneration uniqueIdGeneration;

    public InterestRateLogService(InterestRateLogRepository interestRateLogRepository,
            UniqueIdGeneration uniqueIdGeneration) {
        this.interestRateLogRepository = interestRateLogRepository;
        this.uniqueIdGeneration = uniqueIdGeneration;
    }

    @Cacheable("interestRateLogs")
    public List<InterestRateLog> getAllInterestRateLogs() {
        return interestRateLogRepository.findAll();
    }

    @Cacheable(value = "interestRateLogs", key = "#uniqueId")
    public InterestRateLog getInterestRateLogByUniqueId(String uniqueId) {
        return interestRateLogRepository.findByUniqueId(uniqueId).orElse(null);
    }

    @CachePut(value = "interestRateLogs", key = "#interestRateLog.uniqueId")
    public InterestRateLog saveInterestRateLog(InterestRateLog interestRateLog) {
        if (interestRateLog.getUniqueId() == null || interestRateLog.getUniqueId().isEmpty()) {
            interestRateLog.setUniqueId(uniqueIdGeneration.generateUniqueId());
        }
        return interestRateLogRepository.save(interestRateLog);
    }

    @CacheEvict(value = "interestRateLogs", key = "#uniqueId")
    public void deleteInterestRateLog(String uniqueId) {
        interestRateLogRepository.deleteByUniqueId(uniqueId);
    }
}
