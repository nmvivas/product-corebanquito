package com.banquito.core.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.banquito.core.product.model.InterestRate;
import com.banquito.core.product.repository.InterestRateRepository;
import com.banquito.core.product.service.InterestRateService;
import com.banquito.core.product.util.UniqueIdGeneration;

public class InterestRateServiceTest {

    @Mock
    private InterestRateRepository interestRateRepository;

    @Mock
    private UniqueIdGeneration uniqueIdGeneration;

    @InjectMocks
    private InterestRateService interestRateService;

    private InterestRate interestRate1;
    private InterestRate interestRate2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        interestRate1 = new InterestRate();
        interestRate1.setCode("IR001");
        interestRate1.setUniqueId("123");
        interestRate1.setName("Standard Rate");
        interestRate1.setType("FIX");
        interestRate1.setDaysInMonth(new BigDecimal("30"));
        interestRate1.setDaysInYear(new BigDecimal("365"));

        interestRate2 = new InterestRate();
        interestRate2.setCode("IR002");
        interestRate2.setUniqueId("456");
        interestRate2.setName("Premium Rate");
        interestRate2.setType("VAR");
        interestRate2.setDaysInMonth(new BigDecimal("30"));
        interestRate2.setDaysInYear(new BigDecimal("365"));
    }

    @Test
    void testGetAllInterestRates() {
        // Configurar el mock
        when(interestRateRepository.findAll()).thenReturn(Arrays.asList(interestRate1, interestRate2));

        // Ejecutar el método
        List<InterestRate> result = interestRateService.getAllInterestRates();

        // Verificar el resultado
        assertEquals(2, result.size());
        assertEquals("IR001", result.get(0).getCode());
        assertEquals("IR002", result.get(1).getCode());
    }

    @Test
    void testGetInterestRateByUniqueId() {
        // Configurar el mock
        when(interestRateRepository.findByUniqueId("123")).thenReturn(Optional.of(interestRate1));

        // Ejecutar el método
        InterestRate result = interestRateService.getInterestRateByUniqueId("123");

        // Verificar el resultado
        assertNotNull(result);
        assertEquals("123", result.getUniqueId());
        assertEquals("Standard Rate", result.getName());
    }

    @Test
    void testGetInterestRateByUniqueId_NotFound() {
        // Configurar el mock
        when(interestRateRepository.findByUniqueId("999")).thenReturn(Optional.empty());

        // Ejecutar el método
        InterestRate result = interestRateService.getInterestRateByUniqueId("999");

        // Verificar el resultado
        assertNull(result);
    }

    @Test
    void testSaveInterestRate_WithGeneratedUniqueId() {
        // Configurar el mock
        when(uniqueIdGeneration.generateUniqueId()).thenReturn("789");
        when(interestRateRepository.save(any(InterestRate.class))).thenReturn(interestRate1);

        // Crear un InterestRate sin uniqueId
        InterestRate newRate = new InterestRate();
        newRate.setCode("IR003");
        newRate.setName("Special Rate");
        newRate.setType("VAR");
        newRate.setDaysInMonth(new BigDecimal("30"));
        newRate.setDaysInYear(new BigDecimal("360"));

        when(interestRateRepository.save(any(InterestRate.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ejecutar el método
        InterestRate result = interestRateService.saveInterestRate(newRate);

        // Verificar el resultado
        assertNotNull(result.getUniqueId());
        assertEquals("789", result.getUniqueId());
        verify(interestRateRepository, times(1)).save(any(InterestRate.class));
    }

    @Test
    void testSaveInterestRate_WithExistingUniqueId() {
        // Configurar el mock
        when(interestRateRepository.save(any(InterestRate.class))).thenReturn(interestRate1);

        // Crear un InterestRate con un uniqueId existente
        InterestRate existingRate = new InterestRate();
        existingRate.setCode("IR001");
        existingRate.setUniqueId("123");
        existingRate.setName("Standard Rate");
        existingRate.setType("FIX");
        existingRate.setDaysInMonth(new BigDecimal("30"));
        existingRate.setDaysInYear(new BigDecimal("365"));

        // Ejecutar el método
        InterestRate result = interestRateService.saveInterestRate(existingRate);

        // Verificar el resultado
        assertEquals("123", result.getUniqueId());
        verify(interestRateRepository, times(1)).save(existingRate);
    }

    @Test
    void testDeleteInterestRate() {
        // Ejecutar el método
        interestRateService.deleteInterestRate("123");

        // Verificar que se haya llamado el método correcto en el repositorio
        verify(interestRateRepository, times(1)).deleteByUniqueId("123");
    }
}
