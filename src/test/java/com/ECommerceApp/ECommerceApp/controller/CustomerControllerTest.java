package com.ECommerceApp.ECommerceApp.controller;


import com.ECommerceApp.ECommerceApp.exception.CustomerNotFoundException;
import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerService;


    private Customer customer;
    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setId(1);
    }

    @Test
    void CustomerController_SaveCustomer() throws Exception {
        when(customerService.getCustomer("1")).thenReturn(customer);
        mockMvc.perform( get("/customer/1")).andExpect(status().isOk());
    }

    @Test
    void CustomerController_NotFoundCustomer() throws Exception {
        when(customerService.getCustomer("2")).thenThrow(new CustomerNotFoundException());
        mockMvc.perform( get("/customer/2")).andExpect(status().isNotFound());
    }
}
