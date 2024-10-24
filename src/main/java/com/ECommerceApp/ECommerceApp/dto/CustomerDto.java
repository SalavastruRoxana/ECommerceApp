package com.ECommerceApp.ECommerceApp.dto;

import com.ECommerceApp.ECommerceApp.model.*;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

// should be a reflection of what the user interface or the api response demands.
// includes data from related tables
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private List<Shipment> shipments;
    private List<Order> orders;
    private List<Payment> payments;
    private Cart cart;
    private String password;

    public Customer toCustomer() {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setState(state);
        customer.setZip(zip);
        customer.setCountry(country);
        customer.setShipments(shipments);
        customer.setOrders(orders);
        customer.setCart(cart);
        customer.setPassword(password);
        customer.setCart(new Cart());
        return customer;
    }
}
