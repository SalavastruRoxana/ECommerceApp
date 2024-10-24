package com.ECommerceApp.ECommerceApp.service;


import com.ECommerceApp.ECommerceApp.model.Product;

public interface ProductService {
    public Product createProduct(Product product);
    public Product getProduct(String productId);
}
