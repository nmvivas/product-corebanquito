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

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
        RequestMethod.PUT })
@RestController
@RequestMapping("/api/v1/interest-rate")
public class InterestRateController {

    private final InterestRateService interestRateService;

    public InterestRateController(InterestRateService interestRateService) {
        this.interestRateService = interestRateService;
    }

    @GetMapping
    public List<InterestRateDTO> getAllInterestRates() {
        return interestRateService.getAllInterestRates().stream()
                .map(InterestRateMapper.INSTANCE::toInterestRateDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public InterestRateDTO getInterestRateById(@PathVariable("id") String code) {
        return InterestRateMapper.INSTANCE.toInterestRateDTO(interestRateService.getInterestRateById(code));
    }

    @PostMapping
    public InterestRateDTO createInterestRate(@RequestBody InterestRateDTO interestRateDTO) {
        return InterestRateMapper.INSTANCE.toInterestRateDTO(
                interestRateService.saveInterestRate(InterestRateMapper.INSTANCE.toInterestRate(interestRateDTO)));
    }

    @PutMapping("/{id}")
    public InterestRateDTO updateInterestRate(@PathVariable("id") String code,
            @RequestBody InterestRateDTO interestRateDTO) {
        // Since DTOs are immutable, recreate the DTO with the code
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

    @DeleteMapping("/{id}")
    public void deleteInterestRate(@PathVariable("id") String code) {
        interestRateService.deleteInterestRate(code);
    }
}
