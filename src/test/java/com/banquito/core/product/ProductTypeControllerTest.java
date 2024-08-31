package com.banquito.core.product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.banquito.core.product.controller.ProductTypeController;
import com.banquito.core.product.model.ProductType;
import com.banquito.core.product.service.ProductTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductTypeController.class)
public class ProductTypeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductTypeService productTypeService;

    private ProductType productType1;
    private ProductType productType2;

    @BeforeEach
    void setUp() {
        productType1 = new ProductType();
        productType1.setUniqueId("123");
        productType1.setName("Type 1");
        productType2 = new ProductType();
        productType2.setUniqueId("456");
        productType2.setName("Type 2");
    }
    @Test
    void testGetAllProductTypes() throws Exception {
        // Configurar el mock
        List<ProductType> productTypes = Arrays.asList(productType1, productType2);
        when(productTypeService.getAllProductTypes()).thenReturn(productTypes);

        // Ejecutar y verificar la solicitud
        mockMvc.perform(get("/product-microservice/api/v1/product-type")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].uniqueId").value("123"))
                .andExpect(jsonPath("$[1].uniqueId").value("456"));

        verify(productTypeService, times(1)).getAllProductTypes();
    }

    @Test
    void testGetProductTypeByUniqueId() throws Exception {
        // Configurar el mock
        when(productTypeService.getProductTypeByUniqueId("123")).thenReturn(productType1);

        // Ejecutar y verificar la solicitud
        mockMvc.perform(get("/product-microservice/api/v1/product-type/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueId").value("123"))
                .andExpect(jsonPath("$.name").value("Type 1"));

        verify(productTypeService, times(1)).getProductTypeByUniqueId("123");
    }

    @Test
    void testCreateProductType() throws Exception {
        // Configurar el mock
        when(productTypeService.saveProductType(any(ProductType.class))).thenReturn(productType1);

        // Ejecutar y verificar la solicitud
        mockMvc.perform(post("/product-microservice/api/v1/product-type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(productType1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueId").value("123"))
                .andExpect(jsonPath("$.name").value("Type 1"));

        verify(productTypeService, times(1)).saveProductType(any(ProductType.class));
    }

    @Test
    void testUpdateProductType() throws Exception {
        // Configurar el mock
        when(productTypeService.saveProductType(any(ProductType.class))).thenReturn(productType1);

        // Ejecutar y verificar la solicitud
        mockMvc.perform(put("/product-microservice/api/v1/product-type/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(productType1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueId").value("123"))
                .andExpect(jsonPath("$.name").value("Type 1"));

        verify(productTypeService, times(1)).saveProductType(any(ProductType.class));
    }

    @Test
    void testDeleteProductType() throws Exception {
        // Ejecutar y verificar la solicitud
        mockMvc.perform(delete("/product-microservice/api/v1/product-type/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productTypeService, times(1)).deleteProductType("123");
    }

}
