package com.banquito.core.product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.banquito.core.product.controller.ProductController;
import com.banquito.core.product.model.Product;
import com.banquito.core.product.service.ProductService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setUniqueId("123");
        product1.setName("Product 1");

        product2 = new Product();
        product2.setUniqueId("456");
        product2.setName("Product 2");
    }
    
    @Test
    void testGetAllProducts() throws Exception {
        System.out.println("Iniciando testGetAllProducts");
    
        // Dado que
        List<Product> products = Arrays.asList(product1, product2);
        when(productService.getAllProducts()).thenReturn(products);
    
        // Cuando y Entonces
        mockMvc.perform(get("/product-microservice/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].uniqueId").value("123"))
                .andExpect(jsonPath("$[1].uniqueId").value("456"));
        System.out.println("La solicitud fue exitosa");
    
        System.out.println("testGetAllProductsController pasó exitosamente");
    }

    @Test
    void testGetProductByUniqueId() throws Exception {
        // Dado que
        when(productService.getProductByUniqueId("123")).thenReturn(product1);

        // Cuando y Entonces
        mockMvc.perform(get("/product-microservice/api/v1/product/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueId").value("123"))
                .andExpect(jsonPath("$.name").value("Product 1"));
    }

    @Test
    void testCreateProduct() throws Exception {
        // Dado que
        when(productService.saveProduct(any(Product.class))).thenReturn(product1);

        // Cuando y Entonces
        mockMvc.perform(post("/product-microservice/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Product 1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueId").value("123"))
                .andExpect(jsonPath("$.name").value("Product 1"));
    }

    @Test
    void testUpdateProduct() throws Exception {

        Product updatedProduct = new Product();
        updatedProduct.setUniqueId("123");
        updatedProduct.setName("Updated Product");
        // Dado que
        when(productService.saveProduct(any(Product.class))).thenReturn(updatedProduct);

        // Cuando y Entonces
        mockMvc.perform(put("/product-microservice/api/v1/product/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Updated Product\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueId").value("123"))
                .andExpect(jsonPath("$.name").value("Updated Product"));
        System.out.println("testUpdateProductController pasó exitosamente");
    }

    @Test
    void testDeleteProduct() throws Exception {
        // Cuando y Entonces
        mockMvc.perform(delete("/product-microservice/api/v1/product/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteProduct("123");
    }
}
