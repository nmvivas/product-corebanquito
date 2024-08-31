package com.banquito.core.product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.banquito.core.product.model.ProductType;
import com.banquito.core.product.repository.ProductTypeRepository;
import com.banquito.core.product.service.ProductTypeService;
import com.banquito.core.product.util.UniqueIdGeneration;

public class ProductTypeServiceTest {
    @Mock
    private ProductTypeRepository productTypeRepository;

    @Mock
    private UniqueIdGeneration uniqueIdGeneration;

    @InjectMocks
    private ProductTypeService productTypeService;

    private ProductType productType1;
    private ProductType productType2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        productType1 = new ProductType();
        productType1.setUniqueId("123");
        productType1.setName("Product Type 1");

        productType2 = new ProductType();
        productType2.setUniqueId("456");
        productType2.setName("Product Type 2");
    }

    @Test
    void testGetAllProductTypes() {
        // Dado que
        when(productTypeRepository.findAll()).thenReturn(Arrays.asList(productType1, productType2));

        // Cuando
        List<ProductType> result = productTypeService.getAllProductTypes();

        // Entonces
        verify(productTypeRepository, times(1)).findAll();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyInAnyOrder(productType1, productType2);
    }
        @Test
    void testGetProductTypeByUniqueId() {
        // Dado que
        when(productTypeRepository.findByUniqueId("123")).thenReturn(Optional.of(productType1));

        // Cuando
        ProductType result = productTypeService.getProductTypeByUniqueId("123");

        // Entonces
        verify(productTypeRepository, times(1)).findByUniqueId("123");
        assertThat(result).isEqualTo(productType1);
    }

    @Test
    void testSaveProductType_WithExistingUniqueId() {
        // Dado que
        when(productTypeRepository.save(productType1)).thenReturn(productType1);

        // Cuando
        ProductType result = productTypeService.saveProductType(productType1);

        // Entonces
        verify(productTypeRepository, times(1)).save(productType1);
        assertThat(result).isEqualTo(productType1);
    }

    @Test
    void testSaveProductType_WithNewUniqueId() {
        // Dado que
        ProductType newProductType = new ProductType();
        newProductType.setName("New Type");
        when(uniqueIdGeneration.generateUniqueId()).thenReturn("789");
        when(productTypeRepository.save(newProductType)).thenAnswer(invocation -> {
            ProductType pt = invocation.getArgument(0);
            pt.setUniqueId("789");
            return pt;
        });

        // Cuando
        ProductType result = productTypeService.saveProductType(newProductType);

        // Entonces
        verify(uniqueIdGeneration, times(1)).generateUniqueId();
        verify(productTypeRepository, times(1)).save(any(ProductType.class));
        assertThat(result.getUniqueId()).isEqualTo("789");
    }

    @Test
    void testDeleteProductType() {
        // Cuando
        productTypeService.deleteProductType("123");

        // Entonces
        verify(productTypeRepository, times(1)).deleteByUniqueId("123");
    }
}
