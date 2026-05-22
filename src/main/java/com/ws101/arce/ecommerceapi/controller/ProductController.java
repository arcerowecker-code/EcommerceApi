package com.ws101.arce.ecommerceapi.controller;

import com.ws101.arce.ecommerceapi.model.Product;
import com.ws101.arce.ecommerceapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5500") 
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // GET: Fetch all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // POST: Add a new product (ADD THIS METHOD)
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
}