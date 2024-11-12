package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.Cart;
import com.ECommerceApp.ECommerceApp.model.CartContent;
import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.model.Product;
import com.ECommerceApp.ECommerceApp.repository.CartRepository;
import com.ECommerceApp.ECommerceApp.repository.CustomerRepository;
import com.ECommerceApp.ECommerceApp.repository.ProductRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Cart saveCart(Cart cart) {
        return null;
    }

    @Override
    public void emptyCart(Cart cart) {

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
