package com.banquito.core.products.controller;

import com.banquito.core.products.model.Product;
import com.banquito.core.products.model.ProductPK;
import com.banquito.core.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
        RequestMethod.PUT })
@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(this.productService.getAllProducts());
    }

    @GetMapping("/{codeProductType}/{code}")
    public ResponseEntity<Product> getProductById(@PathVariable String codeProductType, @PathVariable String code) {
        try {
            ProductPK pk = new ProductPK(codeProductType, code);
            return ResponseEntity.ok(this.productService.getProductById(pk));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(this.productService.createProduct(product));
    }

    @PutMapping("/{codeProductType}/{code}")
    public ResponseEntity<Product> updateProduct(@PathVariable String codeProductType, @PathVariable String code,
            @RequestBody Product product) {
        ProductPK pk = new ProductPK(codeProductType, code);
        return ResponseEntity.ok(this.productService.updateProduct(pk, product));
    }

    @DeleteMapping("/{codeProductType}/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String codeProductType, @PathVariable String code) {
        ProductPK pk = new ProductPK(codeProductType, code);
        this.productService.deleteProduct(pk);
        return ResponseEntity.noContent().build();
    }
}
