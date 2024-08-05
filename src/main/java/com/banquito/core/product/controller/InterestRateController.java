package com.banquito.core.product.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.banquito.core.product.dto.InterestRateDTO;
import com.banquito.core.product.service.InterestRateService;
import com.banquito.core.product.util.mapper.InterestRateMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
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
    public List<InterestRateDTO> getAllInterestRates() {
        return interestRateService.getAllInterestRates().stream()
                .map(InterestRateMapper.INSTANCE::toInterestRateDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get an interest rate by Id", description = "Get a single interest rate by its ID")
    @GetMapping("/{id}")
    public InterestRateDTO getInterestRateById(@PathVariable("id") String code) {
        return InterestRateMapper.INSTANCE.toInterestRateDTO(interestRateService.getInterestRateById(code));
    }

    @Operation(summary = "Add an interest rate", description = "Create a new interest rate")
    @PostMapping
    public InterestRateDTO createInterestRate(@RequestBody InterestRateDTO interestRateDTO) {
        return InterestRateMapper.INSTANCE.toInterestRateDTO(
                interestRateService.saveInterestRate(InterestRateMapper.INSTANCE.toInterestRate(interestRateDTO)));
    }

    @Operation(summary = "Update an interest rate", description = "Update an existing interest rate")
    @PutMapping("/{id}")
    public InterestRateDTO updateInterestRate(@PathVariable("id") String code,
            @RequestBody InterestRateDTO interestRateDTO) {
        InterestRateDTO updatedDTO = InterestRateDTO.builder()
                .code(code)
                .name(interestRateDTO.getName())
                .type(interestRateDTO.getType())
                .daysInMonth(interestRateDTO.getDaysInMonth())
                .daysInYear(interestRateDTO.getDaysInYear())
                .build();

        return InterestRateMapper.INSTANCE.toInterestRateDTO(
                interestRateService.saveInterestRate(InterestRateMapper.INSTANCE.toInterestRate(updatedDTO)));
    }

    @Operation(summary = "Delete an interest rate", description = "Delete an existing interest rate by its ID")
    @DeleteMapping("/{id}")
    public void deleteInterestRate(@PathVariable("id") String code) {
        interestRateService.deleteInterestRate(code);
    }
}
