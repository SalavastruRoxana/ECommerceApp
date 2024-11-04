package com.ECommerceApp.ECommerceApp.controller;
import com.ECommerceApp.ECommerceApp.exception.CustomerExceptions;
import com.ECommerceApp.ECommerceApp.exception.CustomerNotFoundException;
import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.service.CustomerService;
import com.ECommerceApp.ECommerceApp.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("api/")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }


//    @GetMapping("/username/{username}")
//    public GenericResponse<UserDto> getByUsername(@PathVariable("username") String username){
//        User user = userService.findUserByUserName(username)
//                .orElseThrow(UserNotFoundException::new);
//        UserDto mappedUser = userMapper.fromUser(user);
//        return GenericResponse.success(mappedUser);
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
    public Customer updateCustomer(@RequestBody Customer customer) {
        Customer cus = customerService.getCustomer(String.valueOf(customer.getId()));
        cus.setFirstName(customer.getFirstName());
        customerService.updateCustomer(cus);
        return cus;
    }

}
