package com.banquito.core.product.service;

import com.banquito.core.product.model.InterestRate;
import com.banquito.core.product.repository.InterestRateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestRateService {

    private final InterestRateRepository interestRateRepository;

    public InterestRateService(InterestRateRepository interestRateRepository) {
        this.interestRateRepository = interestRateRepository;
    }

    public List<InterestRate> getAllInterestRates() {
        return interestRateRepository.findAll();
    }

    public InterestRate getInterestRateById(String code) {
        return interestRateRepository.findById(code).orElse(null);
    }

    public InterestRate saveInterestRate(InterestRate interestRate) {
        return interestRateRepository.save(interestRate);
    }

    public void deleteInterestRate(String code) {
        interestRateRepository.deleteById(code);
    }
}
