package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ProductController {

    private Map<Integer, Product> products = new HashMap<>();

    @PostMapping(value = "/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        this.products.put(product.getId(), product);
        String message = "Product is created successfully, id is " + product.getId();
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<Map<Integer, Product>> getProduct() {
        return new ResponseEntity<>(this.products, HttpStatus.OK);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
        return new ResponseEntity<>(this.products.get(id), HttpStatus.OK);
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        Product entity = this.products.get(id);
        entity.setId(product.getId());
        entity.setName(product.getName());
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        this.products.remove(id);
        String message = "Product is deleted successfully, id is " + id;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
