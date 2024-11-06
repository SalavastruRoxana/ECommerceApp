package com.ECommerceApp.ECommerceApp.util;

import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.service.CustomerService;

public interface CustomerDecorator extends CustomerService {
    public Customer patchCustomer(Customer newCustomer, Customer currentCustomer);
}
