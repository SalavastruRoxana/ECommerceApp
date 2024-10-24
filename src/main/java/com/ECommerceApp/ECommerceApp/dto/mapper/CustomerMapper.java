package com.ECommerceApp.ECommerceApp.dto.mapper;

import com.ECommerceApp.ECommerceApp.dto.CustomerDto;
import com.ECommerceApp.ECommerceApp.model.Customer;

public class CustomerMapper {
    public static CustomerDto toCustomerDto(Customer customer) {
        return new CustomerDto()
                .setId(customer.getId())
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setEmail(customer.getEmail())
                .setPhone(customer.getPhone())
                .setAddress(customer.getAddress())
                .setCity(customer.getCity())
                .setState(customer.getState())
                .setCountry(customer.getCountry())
                .setZip(customer.getZip())
                .setCountry(customer.getCountry())
                .setShipments(customer.getShipments());
//                .setOrders(customer.getOrders())
//                .setCart(customer.getCart());

    }
}
