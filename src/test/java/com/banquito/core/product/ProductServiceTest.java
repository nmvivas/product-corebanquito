package com.banquito.core.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.banquito.core.product.model.Product;
import com.banquito.core.product.repository.ProductRepository;
import com.banquito.core.product.service.ProductService;
import com.banquito.core.product.util.UniqueIdGeneration;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private UniqueIdGeneration uniqueIdGeneration;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    void testGetAllProducts() {
        // Dado que
        Product product1 = new Product();
        Product product2 = new Product();
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Cuando
        List<Product> products = productService.getAllProducts();

        // Entonces
        assertEquals(2, products.size(), "El tamaño de la lista de productos debería ser 2");
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductByUniqueId() {
        // Dado que
        String uniqueId = "CVZ0047878";
        Product product = new Product();
        when(productRepository.findByUniqueId(uniqueId)).thenReturn(Optional.of(product));

        // Cuando
        Product foundProduct = productService.getProductByUniqueId(uniqueId);

        // Entonces
        assertNotNull(foundProduct);
        verify(productRepository, times(1)).findByUniqueId(uniqueId);

        System.out.println("testGetProductByUniqueId pasó exitosamente");
    }

    @Test
    void testSaveProduct_NewProduct() {
        // Dado que
        Product product = new Product();
        product.setUniqueId(null);
        String generatedUniqueId = "DHB0012598";
        when(uniqueIdGeneration.generateUniqueId()).thenReturn(generatedUniqueId);
    
        // Configura el mock para que el producto guardado tenga el uniqueId generado
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product savedProduct = invocation.getArgument(0);
            savedProduct.setUniqueId(generatedUniqueId);
            return savedProduct;
        });
    
        // Cuando
        Product savedProduct = productService.saveProduct(product);
    
        // Entonces
        assertNotNull(savedProduct);
        assertEquals(generatedUniqueId, savedProduct.getUniqueId());
        verify(uniqueIdGeneration, times(1)).generateUniqueId();
        verify(productRepository, times(1)).save(product);
    
        System.out.println("testSaveProduct_NewProduct pasó exitosamente");
    }

    @Test
    void testDeleteProduct() {
        // Dado que
        String uniqueId = "SWS0038193";

        // Cuando
        productService.deleteProduct(uniqueId);

        // Entonces
        verify(productRepository, times(1)).deleteByUniqueId(uniqueId);

        System.out.println("testDeleteProduct pasó exitosamente");
    }


}
