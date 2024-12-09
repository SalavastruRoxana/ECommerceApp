package com.ECommerceApp.ECommerceApp.controller;

import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.model.Order;
import com.ECommerceApp.ECommerceApp.response.GenericResponse;
import com.ECommerceApp.ECommerceApp.service.CustomerService;
import com.ECommerceApp.ECommerceApp.service.CustomerServiceImpl;
import com.ECommerceApp.ECommerceApp.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class OrderController {
    private final OrderService orderService;
    private final CustomerServiceImpl customerService;

    public OrderController(OrderService orderService, CustomerServiceImpl customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @GetMapping("/placeOrder")
    public GenericResponse<Order> placeOrder(){
        Customer customer = customerService.getCustomerFromContext();
        Order order = orderService.createOrder(customer);// TODO: treat the exception customer null
        return GenericResponse.success(order, "Order placed succesfully");
    }
}
