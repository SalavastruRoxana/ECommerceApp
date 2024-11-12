package com.ECommerceApp.ECommerceApp.controller;

import com.ECommerceApp.ECommerceApp.model.CartContent;
import com.ECommerceApp.ECommerceApp.response.GenericResponse;
import com.ECommerceApp.ECommerceApp.service.CartService;
import com.ECommerceApp.ECommerceApp.service.CartServiceImpl;
import com.ECommerceApp.ECommerceApp.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class CartController {
    @Autowired
    private final CartServiceImpl cartService;
    private final CartServiceImpl cartServiceImpl;

    public CartController(CartServiceImpl cartService, CartServiceImpl cartServiceImpl) {
        this.cartService = cartService;
        this.cartServiceImpl = cartServiceImpl;
    }

    @PostMapping("/cart/{productId}/{quantity}")
    public GenericResponse<CartContent> addProductToCart(@PathVariable String productId, @PathVariable int quantity) {
        Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authenticationToken.getPrincipal();
        CartContent content =  cartServiceImpl.addProductToCart(user, productId, quantity);

        return GenericResponse.success(content, "Product successfully added to the cart");
    }
}
