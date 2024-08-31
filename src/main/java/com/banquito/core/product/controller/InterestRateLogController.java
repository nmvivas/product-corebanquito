package com.banquito.core.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.product.model.InterestRateLog;
import com.banquito.core.product.service.InterestRateLogService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
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
    public List<InterestRateLog> getAllInterestRateLogs() {
        return interestRateLogService.getAllInterestRateLogs();
    }

    @Operation(summary = "Get an interest rate log by uniqueId", description = "Get a single interest rate log by its uniqueId")
    @GetMapping("/{uniqueId}")
    public InterestRateLog getInterestRateLogByUniqueId(@PathVariable("uniqueId") String uniqueId) {
        return interestRateLogService.getInterestRateLogByUniqueId(uniqueId);
    }

    @Operation(summary = "Add an interest rate log", description = "Create a new interest rate log")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InterestRateLog createInterestRateLog(@RequestBody InterestRateLog interestRateLog) {
        return interestRateLogService.saveInterestRateLog(interestRateLog);
    }

    @Operation(summary = "Update an interest rate log", description = "Update an existing interest rate log")
    @PutMapping("/{uniqueId}")
    public InterestRateLog updateInterestRateLog(@PathVariable("uniqueId") String uniqueId, @RequestBody InterestRateLog interestRateLog) {
        interestRateLog.setUniqueId(uniqueId);
        return interestRateLogService.saveInterestRateLog(interestRateLog);
    }

    @Operation(summary = "Delete an interest rate log", description = "Delete an existing interest rate log by its uniqueId")
    @DeleteMapping("/{uniqueId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInterestRateLog(@PathVariable("uniqueId") String uniqueId) {
        interestRateLogService.deleteInterestRateLog(uniqueId);
    }
}
