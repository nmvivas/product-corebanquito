package com.banquito.core.product.service;

import com.banquito.core.product.model.InterestRateLog;
import com.banquito.core.product.repository.InterestRateLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestRateLogService {

    private final InterestRateLogRepository interestRateLogRepository;

    public InterestRateLogService(InterestRateLogRepository interestRateLogRepository) {
        this.interestRateLogRepository = interestRateLogRepository;
    }

    public List<InterestRateLog> getAllInterestRateLogs() {
        return interestRateLogRepository.findAll();
    }

    public InterestRateLog getInterestRateLogById(Integer id) {
        return interestRateLogRepository.findById(id).orElse(null);
    }

    public InterestRateLog saveInterestRateLog(InterestRateLog interestRateLog) {
        return interestRateLogRepository.save(interestRateLog);
    }

    public void deleteInterestRateLog(Integer id) {
        interestRateLogRepository.deleteById(id);
    }
}
