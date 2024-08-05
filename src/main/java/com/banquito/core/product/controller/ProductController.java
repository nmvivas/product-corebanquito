package com.banquito.core.product.controller;

import java.util.List;

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

import com.banquito.core.product.model.Product;
import com.banquito.core.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping("/product-microservice/api/v1/product")
@Tag(name = "Product Management System", description = "Operations pertaining to products in Product Management System")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "View a list of available products", description = "Get a list of all products")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(summary = "Get a product by uniqueId", description = "Get a single product by its uniqueId")
    @GetMapping("/{uniqueId}")
    public Product getProductByUniqueId(@PathVariable String uniqueId) {
        return productService.getProductByUniqueId(uniqueId);
    }

    @Operation(summary = "Add a product", description = "Create a new product")
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @Operation(summary = "Update a product", description = "Update an existing product")
    @PutMapping("/{uniqueId}")
    public Product updateProduct(@PathVariable String uniqueId, @RequestBody Product product) {
        product.setUniqueId(uniqueId);
        return productService.saveProduct(product);
    }

    @Operation(summary = "Delete a product", description = "Delete an existing product by its uniqueId")
    @DeleteMapping("/{uniqueId}")
    public void deleteProduct(@PathVariable String uniqueId) {
        productService.deleteProduct(uniqueId);
    }
}
