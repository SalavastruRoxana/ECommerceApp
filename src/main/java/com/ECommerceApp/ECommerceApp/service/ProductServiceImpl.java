package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.Cart;
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
        CartContent content = new CartContent();
        Product product = productRepository.findById(Integer.parseInt(productId));
        Customer cus = customerRepository.findByEmail( user.getUsername()).orElse(null);
        if (cus != null)
        {
            Cart cart = cus.getCart();
            content.setCart(cart);
        }
        content.setProduct( product );
        content.setQuantity(quantity);

        return content;
    }

}
