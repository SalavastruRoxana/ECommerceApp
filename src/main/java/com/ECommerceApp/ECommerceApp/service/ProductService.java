package com.ECommerceApp.ECommerceApp.service;


import com.ECommerceApp.ECommerceApp.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProduct(String productId);
    List<Product> getProducts();
}
