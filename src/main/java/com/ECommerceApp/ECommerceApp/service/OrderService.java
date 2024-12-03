package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.model.Order;


public interface OrderService {
    public Order createOrder(Customer customer);
}
