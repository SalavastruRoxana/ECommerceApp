package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.CartContent;
import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.model.Product;
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

    public CartContent addProductToCart(User user, String productId, int quantity) {
        Customer customer = customerRepository.findByEmail( user.getUsername()).orElse(null);
        CartContent content = new CartContent();
        Product product = productRepository.findById(Integer.parseInt(productId));

        content.setProduct( product );
        content.setQuantity(quantity);
//        if (customer != null) {
//            List<CartContent> contentList = new ArrayList<>();
//            contentList.add(content);
//            //product.se(contentList);
//            productRepository.save(product); // update product might not be needed
//        }

        return content;
    }

}
