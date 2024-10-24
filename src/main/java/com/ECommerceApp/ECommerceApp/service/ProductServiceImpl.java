package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.Product;
import com.ECommerceApp.ECommerceApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product getProduct(String productId) {
        return this.productRepository.findById(Integer.parseInt(productId));
    }

    public List<Product> getProducts() {
        return List.of();
    }

//    public Product addProductToCart(String productId, int customerId) {
//        return this.productRepository.
//    }
}
