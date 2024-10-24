package com.ECommerceApp.ECommerceApp.controller;
import com.ECommerceApp.ECommerceApp.model.Cart;
import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.model.Product;
import com.ECommerceApp.ECommerceApp.repository.CartRepository;
import com.ECommerceApp.ECommerceApp.repository.CustomerRepository;
import com.ECommerceApp.ECommerceApp.repository.ProductRepository;
import com.ECommerceApp.ECommerceApp.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/")
public class ProductController {


    private final ProductServiceImpl productServiceImpl;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    ProductController (ProductServiceImpl productServiceImpl, CartRepository cartRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.productServiceImpl = productServiceImpl;
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productServiceImpl.getProducts();
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable String productId) {
        return productServiceImpl.getProduct(productId);
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) {
        return productServiceImpl.createProduct(product);
    }

    @PostMapping("/product/{productId}/cart")
    public Cart addProductToCart(@PathVariable String productId) {
        Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authenticationToken.getPrincipal();
        Customer customer = customerRepository.findByEmail( user.getUsername()).orElse(null);
        Cart cart = new Cart();
        //I don't have cart customer relationship...,
        // when create customer, add cart to its list
        if (customer != null) {
            cart = customer.getCart();
            Product product = productRepository.findById(Integer.parseInt(productId));
            List<Cart> carts = new ArrayList<>();
            carts.add(cart);
            product.setCart(carts);
            productRepository.save(product);

        }
        return cart;

        //return productServiceImpl.addProductToCart(productId, custom);
    }

}
