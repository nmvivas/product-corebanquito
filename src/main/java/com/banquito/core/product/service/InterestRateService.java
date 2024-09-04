package com.banquito.core.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
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

    @Cacheable("interestRates")
    public List<InterestRate> getAllInterestRates() {
        return interestRateRepository.findAll();
    }

    @Cacheable(value = "interestRates", key = "#uniqueId")
    public InterestRate getInterestRateByUniqueId(String uniqueId) {
        return interestRateRepository.findByUniqueId(uniqueId).orElse(null);
    }

    @CachePut(value = "interestRates", key = "#interestRate.uniqueId")
    public InterestRate saveInterestRate(InterestRate interestRate) {
        if (interestRate.getUniqueId() == null || interestRate.getUniqueId().isEmpty()) {
            interestRate.setUniqueId(uniqueIdGeneration.generateUniqueId());
        }
        return interestRateRepository.save(interestRate);
    }

    @CacheEvict(value = "interestRates", key = "#uniqueId")
    public void deleteInterestRate(String uniqueId) {
        interestRateRepository.deleteByUniqueId(uniqueId);
    }
}
