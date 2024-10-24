package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.Cart;

public interface CartService {
    public Cart saveCart(Cart cart);
    public void emptyCart(Cart cart);
}
