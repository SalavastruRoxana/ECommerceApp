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
    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;
    //inject payment service
    //inject customer service, delete customerRepository.findByEmail

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.paymentRepository = paymentRepository;
    }


    //TODO: To implement payment method ?!
    // TODO add products in order
    // delete what is in cart
    @Override
    public Order createOrder(User user) {
        Customer cus = customerRepository.findByEmail( user.getUsername()).orElse(null);
        Order order = new Order();

        if (cus != null)
        {
            order.setCustomer(cus);
            Cart cart = cus.getCart();
            order.setDate(Date.valueOf(LocalDate.now()));
            order.setPrice(this.getPrice(cart));
            Payment payment = new Payment();
            //payment.setOrder(order);
            payment.setPaymentDate(Date.valueOf(LocalDate.now()));
            payment.setAmount(this.getPrice(cart));
            payment.setPaymentMethod("Card");

            order.setPayment(payment);

            paymentRepository.save(payment);
            orderRepository.save(order);
        }
        else
            throw new CustomerNotFoundException();

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
