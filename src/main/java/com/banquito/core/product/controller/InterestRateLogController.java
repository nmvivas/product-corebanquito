package com.banquito.core.product.controller;

import com.banquito.core.product.dto.InterestRateLogDTO;
import com.banquito.core.product.service.InterestRateLogService;
import com.banquito.core.product.util.mapper.InterestRateLogMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/interest-rate-logs")
public class InterestRateLogController {

    private final InterestRateLogService interestRateLogService;

    public InterestRateLogController(InterestRateLogService interestRateLogService) {
        this.interestRateLogService = interestRateLogService;
    }

    @GetMapping
    public List<InterestRateLogDTO> getAllInterestRateLogs() {
        return interestRateLogService.getAllInterestRateLogs().stream()
                .map(InterestRateLogMapper.INSTANCE::toInterestRateLogDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public InterestRateLogDTO getInterestRateLogById(@PathVariable("id") Integer id) {
        return InterestRateLogMapper.INSTANCE.toInterestRateLogDTO(interestRateLogService.getInterestRateLogById(id));
    }

    @PostMapping
    public InterestRateLogDTO createInterestRateLog(@RequestBody InterestRateLogDTO interestRateLogDTO) {
        return InterestRateLogMapper.INSTANCE.toInterestRateLogDTO(
                interestRateLogService
                        .saveInterestRateLog(InterestRateLogMapper.INSTANCE.toInterestRateLog(interestRateLogDTO)));
    }

    @PutMapping("/{id}")
    public InterestRateLogDTO updateInterestRateLog(@PathVariable("id") Integer id,
            @RequestBody InterestRateLogDTO interestRateLogDTO) {
        // Since DTOs are immutable, recreate the DTO with the ID
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

    @DeleteMapping("/{id}")
    public void deleteInterestRateLog(@PathVariable("id") Integer id) {
        interestRateLogService.deleteInterestRateLog(id);
    }
}
