package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.Order;
import org.springframework.security.core.userdetails.User;

public interface OrderService {
    public Order createOrder(User user);
}
