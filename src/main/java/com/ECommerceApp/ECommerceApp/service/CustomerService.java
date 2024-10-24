package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.Customer;

import java.util.List;


public interface CustomerService {
    public Customer createCustomer(Customer customer);
    public Customer getCustomer(String customerId);
    public List<Customer> getCustomers();
    public void deleteCustomer(String customerId);
    public void updateCustomer(Customer customer);
}