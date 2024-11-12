package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.*;
import com.ECommerceApp.ECommerceApp.repository.CustomerRepository;
import com.ECommerceApp.ECommerceApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public ProductServiceImpl(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }


    //TODO Make Sku be unique
    @Override
    public Product createProduct(Product product) {

        return this.productRepository.save(product);
    }

    @Override
    public Product getProduct(String productId) {
        return this.productRepository.findById(Integer.parseInt(productId));
    }

    //TODO it returns recursively for category list of products and so on
    public Product getProductBySku(String productSKU) {
        return this.productRepository.findBySku(productSKU);
    }

    //TODO
    public Product getProductByCategory(Category category) {
        return this.productRepository.findByCategory(category);
    }



    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Iterable<Product> productIterable = this.productRepository.findAll();
        productIterable.forEach(products::add);
        return products;
    }


}
