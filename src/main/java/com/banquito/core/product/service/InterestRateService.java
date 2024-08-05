package com.banquito.core.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banquito.core.product.model.InterestRate;
import com.banquito.core.product.repository.InterestRateRepository;
import com.banquito.core.product.util.UniqueIdGeneration;

@Service
public class InterestRateService {

    private final InterestRateRepository interestRateRepository;
    private final UniqueIdGeneration uniqueIdGeneration;

    public InterestRateService(InterestRateRepository interestRateRepository, UniqueIdGeneration uniqueIdGeneration) {
        this.interestRateRepository = interestRateRepository;
        this.uniqueIdGeneration = uniqueIdGeneration;
    }

    public List<InterestRate> getAllInterestRates() {
        return interestRateRepository.findAll();
    }

    public InterestRate getInterestRateByUniqueId(String uniqueId) {
        return interestRateRepository.findByUniqueId(uniqueId).orElse(null);
    }

    public InterestRate saveInterestRate(InterestRate interestRate) {
        if (interestRate.getUniqueId() == null || interestRate.getUniqueId().isEmpty()) {
            interestRate.setUniqueId(uniqueIdGeneration.generateUniqueId());
        }
        return interestRateRepository.save(interestRate);
    }

    public void deleteInterestRate(String uniqueId) {
        interestRateRepository.deleteByUniqueId(uniqueId);
    }
}
