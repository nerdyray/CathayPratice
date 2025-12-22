package com.example.demo.service;

import java.util.Map;

import com.example.demo.model.Product;

public interface ProductService {

    public abstract int createProduct(Product product);

    public abstract Map<Integer, Product> getProduct();

    public abstract Product getProduct(int id);

    public abstract Product updateProduct(int id, Product product);

    public abstract int deleteProduct(int id);
}
