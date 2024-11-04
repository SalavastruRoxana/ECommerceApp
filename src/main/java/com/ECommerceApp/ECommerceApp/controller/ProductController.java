package com.ECommerceApp.ECommerceApp.controller;
import com.ECommerceApp.ECommerceApp.model.Cart;
import com.ECommerceApp.ECommerceApp.model.CartContent;
import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.model.Product;
import com.ECommerceApp.ECommerceApp.repository.CartRepository;
import com.ECommerceApp.ECommerceApp.repository.CustomerRepository;
import com.ECommerceApp.ECommerceApp.repository.ProductRepository;
import com.ECommerceApp.ECommerceApp.response.GenericResponse;
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

    @PostMapping("/product/{productId}/{quantity}/cart")
    public GenericResponse<CartContent> addProductToCart(@PathVariable String productId, @PathVariable int quantity) {
        Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authenticationToken.getPrincipal();
        CartContent content =  productServiceImpl.addProductToCart(user, productId, quantity);

        return GenericResponse.success(content, "Product successfully added to the cart");
    }

}
