package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.Cart;
import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.model.Order;
import com.ECommerceApp.ECommerceApp.model.Shipment;
import com.ECommerceApp.ECommerceApp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ShipmentRepository shipmentRepository;

    CustomerServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository, OrderRepository orderRepository, ShipmentRepository shipmentRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customer1 = this.customerRepository.save(customer);
        Cart cart = new Cart();
        cart.setCustomer(customer1);
        cartRepository.save(cart);
//        Order order = new Order();
//        order.setCustomer(customer1);
//        orderRepository.save(order);
//        Shipment shipment = new Shipment();
//        shipment.setCustomer(customer1);
//        shipmentRepository.save(shipment);
        return customer1;
    }

    @Override
    public Customer getCustomer(String customerId) {
        return this.customerRepository.findById(Integer.parseInt(customerId));
    }

    @Override
    public List<Customer> getCustomers() {
        return List.of();
    }


//    public Customer loginCustomer(String customerUsername, String customerPassword) {
//        return this.customerRepository.findByCredentials(customerUsername, customerPassword);
//    }

    @Override
    public void deleteCustomer(String customerId) {

    }

    @Override
    public void updateCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }
}