package com.banquito.core.product.controller;

import com.banquito.core.product.dto.InterestRateDTO;
import com.banquito.core.product.service.InterestRateService;
import com.banquito.core.product.util.mapper.InterestRateMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
        RequestMethod.PUT })
@RestController
@RequestMapping("/api/v1/interest-rates")
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
