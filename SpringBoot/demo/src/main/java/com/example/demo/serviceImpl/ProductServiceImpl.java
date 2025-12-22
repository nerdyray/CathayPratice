package com.example.demo.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private Map<Integer, Product> products = new HashMap<>();

    @Override
    public int createProduct(Product product) {
        this.products.put(product.getId(), product);
        return product.getId();
    }

    @Override
    public Map<Integer, Product> getProduct() {
        return this.products;
    }

    @Override
    public Product getProduct(int id) {
        return this.products.get(id);
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product entity = this.products.get(id);
        entity.setId(product.getId());
        entity.setName(product.getName());
        return entity;
    }

    @Override
    public int deleteProduct(int id) {
        this.products.remove(id);
        return id;
    }
}
