package com.ECommerceApp.ECommerceApp.controller;
import com.ECommerceApp.ECommerceApp.exception.CustomerNotFoundException;
import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.model.Order;
import com.ECommerceApp.ECommerceApp.response.GenericResponse;
import com.ECommerceApp.ECommerceApp.service.CustomerService;
import com.ECommerceApp.ECommerceApp.service.CustomerServiceImpl;
import com.ECommerceApp.ECommerceApp.service.OrderServiceImpl;
import com.ECommerceApp.ECommerceApp.util.CustomerConcreteDecorator;
import com.ECommerceApp.ECommerceApp.util.CustomerDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("api/")
public class CustomerController {

    //TODO: make them private final?
    @Autowired
    private  CustomerServiceImpl customerService;

    @Autowired
    private OrderServiceImpl orderService;

//    CustomerController(CustomerServiceImpl customerService) {
//        this.customerService = customerService;
//    }

    

    @GetMapping("/customer/{customerId}")
    public Customer getCustomer(@PathVariable String customerId) {
        try {
            return customerService.getCustomer(customerId);
        }catch (CustomerNotFoundException e) {  // or in seevice, or throw it from here, don't return the error body from here
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getCustomers();
    }

    @DeleteMapping("/customer/{customerId}")
    public void deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PatchMapping("/customer/{customerId}")
    public GenericResponse<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable String customerId) {
        Customer currentCustomer = customerService.getCustomer(customerId);
        CustomerConcreteDecorator customerConcreteDecorator = new CustomerConcreteDecorator();
        customerConcreteDecorator.patchCustomer(customer, currentCustomer);
        customerService.updateCustomer(currentCustomer);
        return GenericResponse.success(currentCustomer, "Customer updated successfully");
    }



}
