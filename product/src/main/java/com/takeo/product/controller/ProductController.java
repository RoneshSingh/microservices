package com.takeo.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.takeo.product.model.Product;
import com.takeo.product.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired

    public ProductController(ProductRepository productRepository) {

        this.productRepository = productRepository;

    }

    @GetMapping

    public List<Product> getAllProducts() {

        return productRepository.findAll();

    }

    @PostMapping

    public Product createProduct(@RequestBody Product product) {

        return productRepository.save(product);

    }

    @GetMapping("/{productId}/users")

    public List<Object> getUsersForProduct(@PathVariable Long productId) {

        String userServiceUrl = "http://localhost:8080/api/users";

        ResponseEntity<List<Object>> response = restTemplate.exchange(

                userServiceUrl,

                HttpMethod.GET,

                null,

                new ParameterizedTypeReference<List<Object>>() {
                }

        );

        return response.getBody();

    }

}
