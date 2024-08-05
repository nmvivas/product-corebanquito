package com.banquito.core.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banquito.core.product.model.InterestRate;
import com.banquito.core.product.repository.InterestRateRepository;

@Service
public class InterestRateService {

    private final InterestRateRepository interestRateRepository;

    public InterestRateService(InterestRateRepository interestRateRepository) {
        this.interestRateRepository = interestRateRepository;
    }

    public List<InterestRate> getAllInterestRates() {
        return interestRateRepository.findAll();
    }

    public InterestRate getInterestRateByUniqueId(String uniqueId) {
        return interestRateRepository.findByUniqueId(uniqueId).orElse(null);
    }

    public InterestRate saveInterestRate(InterestRate interestRate) {
        return interestRateRepository.save(interestRate);
    }

    public void deleteInterestRate(String uniqueId) {
        interestRateRepository.deleteByUniqueId(uniqueId);
    }
}
