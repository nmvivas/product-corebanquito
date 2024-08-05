package com.banquito.core.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banquito.core.product.model.InterestRateLog;
import com.banquito.core.product.repository.InterestRateLogRepository;

@Service
public class InterestRateLogService {

    private final InterestRateLogRepository interestRateLogRepository;

    public InterestRateLogService(InterestRateLogRepository interestRateLogRepository) {
        this.interestRateLogRepository = interestRateLogRepository;
    }

    public List<InterestRateLog> getAllInterestRateLogs() {
        return interestRateLogRepository.findAll();
    }

    public InterestRateLog getInterestRateLogByUniqueId(String uniqueId) {
        return interestRateLogRepository.findByUniqueId(uniqueId).orElse(null);
    }

    public InterestRateLog saveInterestRateLog(InterestRateLog interestRateLog) {
        return interestRateLogRepository.save(interestRateLog);
    }

    public void deleteInterestRateLog(String uniqueId) {
        interestRateLogRepository.deleteByUniqueId(uniqueId);
    }
}
