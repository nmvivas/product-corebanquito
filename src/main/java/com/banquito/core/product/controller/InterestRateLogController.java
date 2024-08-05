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

import com.banquito.core.product.dto.InterestRateLogDTO;
import com.banquito.core.product.service.InterestRateLogService;
import com.banquito.core.product.util.mapper.InterestRateLogMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
@RestController
@RequestMapping("/product-microservice/api/v1/interest-rate-log")
@Tag(name = "InterestRateLog Management System", description = "Operations pertaining to interest rate logs in InterestRateLog Management System")
public class InterestRateLogController {

    private final InterestRateLogService interestRateLogService;

    public InterestRateLogController(InterestRateLogService interestRateLogService) {
        this.interestRateLogService = interestRateLogService;
    }

    @Operation(summary = "View a list of available interest rate logs", description = "Get a list of all interest rate logs")
    @GetMapping
    public List<InterestRateLogDTO> getAllInterestRateLogs() {
        return interestRateLogService.getAllInterestRateLogs().stream()
                .map(InterestRateLogMapper.INSTANCE::toInterestRateLogDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get an interest rate log by Id", description = "Get a single interest rate log by its ID")
    @GetMapping("/{id}")
    public InterestRateLogDTO getInterestRateLogById(@PathVariable("id") Integer id) {
        return InterestRateLogMapper.INSTANCE.toInterestRateLogDTO(interestRateLogService.getInterestRateLogById(id));
    }

    @Operation(summary = "Add an interest rate log", description = "Create a new interest rate log")
    @PostMapping
    public InterestRateLogDTO createInterestRateLog(@RequestBody InterestRateLogDTO interestRateLogDTO) {
        return InterestRateLogMapper.INSTANCE.toInterestRateLogDTO(
                interestRateLogService
                        .saveInterestRateLog(InterestRateLogMapper.INSTANCE.toInterestRateLog(interestRateLogDTO)));
    }

    @Operation(summary = "Update an interest rate log", description = "Update an existing interest rate log")
    @PutMapping("/{id}")
    public InterestRateLogDTO updateInterestRateLog(@PathVariable("id") Integer id,
            @RequestBody InterestRateLogDTO interestRateLogDTO) {
        InterestRateLogDTO updatedDTO = InterestRateLogDTO.builder()
                .id(id)
                .codeInterestRate(interestRateLogDTO.getCodeInterestRate())
                .value(interestRateLogDTO.getValue())
                .startDate(interestRateLogDTO.getStartDate())
                .endDate(interestRateLogDTO.getEndDate())
                .state(interestRateLogDTO.getState())
                .build();

        return InterestRateLogMapper.INSTANCE.toInterestRateLogDTO(
                interestRateLogService
                        .saveInterestRateLog(InterestRateLogMapper.INSTANCE.toInterestRateLog(updatedDTO)));
    }

    @Operation(summary = "Delete an interest rate log", description = "Delete an existing interest rate log by its ID")
    @DeleteMapping("/{id}")
    public void deleteInterestRateLog(@PathVariable("id") Integer id) {
        interestRateLogService.deleteInterestRateLog(id);
    }
}
