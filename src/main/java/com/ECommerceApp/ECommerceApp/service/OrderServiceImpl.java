package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.exception.CustomerNotFoundException;
import com.ECommerceApp.ECommerceApp.model.*;
import com.ECommerceApp.ECommerceApp.repository.CustomerRepository;
import com.ECommerceApp.ECommerceApp.repository.OrderRepository;
import com.ECommerceApp.ECommerceApp.repository.PaymentRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }


    //TODO: To implement payment method ?!
    // TODO add products in order
    // delete what is in cart
    @Override
    public Order createOrder(Customer customer) {
        //Customer cus = customerRepository.findByEmail( user.getUsername()).orElse(null);
        Order order = new Order();

        order.setCustomer(customer);
        Cart cart = customer.getCart();
        order.setDate(Date.valueOf(LocalDate.now()));
        order.setPrice(this.getPrice(cart));

        order.setPayment(paymentService.createPayment(this.getPrice(cart)));
        orderRepository.save(order);

        return order;
    }

    private Float getPrice(Cart cart)
    {
        Float sum = Float.valueOf(0);
        List<CartContent> cartContent = cart.getCartContent();
        for(CartContent c : cartContent)
            sum += c.getQuantity() * c.getProduct().getPrice();
        return sum;
    }
}
