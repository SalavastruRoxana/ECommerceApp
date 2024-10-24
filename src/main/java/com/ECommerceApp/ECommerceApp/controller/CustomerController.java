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

//    @PostMapping("/customer/login")
//    public Customer loginCustomer(@RequestBody  String customerUsername, @RequestBody  String customerPassword) {
//        //maybe get an optional and/or see how to handle the case when the mail/password not found/wrong
//        return customerService.loginCustomer(customerUsername, customerPassword);
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

//    @Component
//    public class Patcher {
//        public static void internPatcher(Intern existingIntern, Intern incompleteIntern) throws IllegalAccessException {
//
//            //GET THE COMPILED VERSION OF THE CLASS
//            Class<?> internClass= Intern.class;
//            Field[] internFields=internClass.getDeclaredFields();
//            System.out.println(internFields.length);
//            for(Field field : internFields){
//                System.out.println(field.getName());
//                //CANT ACCESS IF THE FIELD IS PRIVATE
//                field.setAccessible(true);
//
//                //CHECK IF THE VALUE OF THE FIELD IS NOT NULL, IF NOT UPDATE EXISTING INTERN
//                Object value=field.get(incompleteIntern);
//                if(value!=null){
//                    field.set(existingIntern,value);
//                }
//                //MAKE THE FIELD PRIVATE AGAIN
//                field.setAccessible(false);
//            }
//
//        }
//
//    }
}
