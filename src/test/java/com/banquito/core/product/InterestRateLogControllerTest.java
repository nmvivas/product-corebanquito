package com.banquito.core.product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.banquito.core.product.controller.InterestRateLogController;
import com.banquito.core.product.model.InterestRateLog;
import com.banquito.core.product.service.InterestRateLogService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InterestRateLogController.class)
public class InterestRateLogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InterestRateLogService interestRateLogService;

    @Test
    void testGetAllInterestRateLogs() throws Exception {
        InterestRateLog log1 = new InterestRateLog();
        log1.setId(1);
        log1.setUniqueId("123");
        log1.setCodeInterestRate("IR001");
        log1.setValue(new BigDecimal("3.5"));
        log1.setStartDate(new Date());
        log1.setState("ACT");

        InterestRateLog log2 = new InterestRateLog();
        log2.setId(2);
        log2.setUniqueId("456");
        log2.setCodeInterestRate("IR002");
        log2.setValue(new BigDecimal("4.0"));
        log2.setStartDate(new Date());
        log2.setState("ACT");

        when(interestRateLogService.getAllInterestRateLogs()).thenReturn(Arrays.asList(log1, log2));

        mockMvc.perform(get("/product-microservice/api/v1/interest-rate-log"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].uniqueId").value("123"))
            .andExpect(jsonPath("$[1].uniqueId").value("456"));
    }

    @Test
    void testGetInterestRateLogByUniqueId_Found() throws Exception {
        InterestRateLog log = new InterestRateLog();
        log.setId(1);
        log.setUniqueId("123");
        log.setCodeInterestRate("IR001");
        log.setValue(new BigDecimal("3.5"));
        log.setStartDate(new Date());
        log.setState("ACT");

        when(interestRateLogService.getInterestRateLogByUniqueId("123")).thenReturn(log);

        mockMvc.perform(get("/product-microservice/api/v1/interest-rate-log/123"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.uniqueId").value("123"))
            .andExpect(jsonPath("$.codeInterestRate").value("IR001"));
    }

    @Test
    void testGetInterestRateLogByUniqueId_NotFound() throws Exception {
        when(interestRateLogService.getInterestRateLogByUniqueId("999")).thenReturn(null);

        mockMvc.perform(get("/api/interest-rate-logs/999"))
            .andExpect(status().isNotFound());
    }

    @Test
    void testCreateInterestRateLog() throws Exception {
        InterestRateLog log = new InterestRateLog();
        log.setUniqueId("789");
        log.setCodeInterestRate("IR003");
        log.setValue(new BigDecimal("2.5"));
        log.setStartDate(new Date());
        log.setState("ACT");

        InterestRateLog savedLog = new InterestRateLog();
        savedLog.setId(1);
        savedLog.setUniqueId("789");
        savedLog.setCodeInterestRate("IR003");
        savedLog.setValue(new BigDecimal("2.5"));
        savedLog.setStartDate(new Date());
        savedLog.setState("ACT");

        when(interestRateLogService.saveInterestRateLog(any(InterestRateLog.class))).thenReturn(savedLog);

        mockMvc.perform(post("/product-microservice/api/v1/interest-rate-log")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"codeInterestRate\":\"IR003\",\"value\":2.5,\"startDate\":\"2024-08-01\",\"state\":\"ACT\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.uniqueId").value("789"))
            .andExpect(jsonPath("$.codeInterestRate").value("IR003"));
    }

    @Test
    void testDeleteInterestRateLog() throws Exception {
        mockMvc.perform(delete("/product-microservice/api/v1/interest-rate-log/123"))
            .andExpect(status().isNoContent());

        verify(interestRateLogService, times(1)).deleteInterestRateLog("123");
    }
}
