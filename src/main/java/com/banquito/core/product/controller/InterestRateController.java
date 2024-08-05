package com.banquito.core.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.product.model.InterestRate;
import com.banquito.core.product.service.InterestRateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping("/product-microservice/api/v1/interest-rate")
@Tag(name = "InterestRate Management System", description = "Operations pertaining to interest rates in InterestRate Management System")
public class InterestRateController {

    private final InterestRateService interestRateService;

    public InterestRateController(InterestRateService interestRateService) {
        this.interestRateService = interestRateService;
    }

    @Operation(summary = "View a list of available interest rates", description = "Get a list of all interest rates")
    @GetMapping
    public List<InterestRate> getAllInterestRates() {
        return interestRateService.getAllInterestRates();
    }

    @Operation(summary = "Get an interest rate by uniqueId", description = "Get a single interest rate by its uniqueId")
    @GetMapping("/{uniqueId}")
    public InterestRate getInterestRateByUniqueId(@PathVariable("uniqueId") String uniqueId) {
        return interestRateService.getInterestRateByUniqueId(uniqueId);
    }

    @Operation(summary = "Add an interest rate", description = "Create a new interest rate")
    @PostMapping
    public InterestRate createInterestRate(@RequestBody InterestRate interestRate) {
        return interestRateService.saveInterestRate(interestRate);
    }

    @Operation(summary = "Update an interest rate", description = "Update an existing interest rate")
    @PutMapping("/{uniqueId}")
    public InterestRate updateInterestRate(@PathVariable("uniqueId") String uniqueId, @RequestBody InterestRate interestRate) {
        interestRate.setUniqueId(uniqueId);
        return interestRateService.saveInterestRate(interestRate);
    }

    @Operation(summary = "Delete an interest rate", description = "Delete an existing interest rate by its uniqueId")
    @DeleteMapping("/{uniqueId}")
    public void deleteInterestRate(@PathVariable("uniqueId") String uniqueId) {
        interestRateService.deleteInterestRate(uniqueId);
    }
}
