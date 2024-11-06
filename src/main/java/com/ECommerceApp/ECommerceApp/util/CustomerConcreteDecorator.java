package com.ECommerceApp.ECommerceApp.util;

import com.ECommerceApp.ECommerceApp.model.Customer;

import java.util.List;

public class CustomerConcreteDecorator implements CustomerDecorator {

    //TODO: Implement a change password method
    @Override
    public Customer patchCustomer(Customer newCustomer, Customer currentCustomer) {
        if (newCustomer.getFirstName() != null && !newCustomer.getFirstName().equals(currentCustomer.getFirstName())) {
            currentCustomer.setFirstName(newCustomer.getFirstName());
        }
        if (newCustomer.getLastName() != null && !newCustomer.getLastName().equals(currentCustomer.getLastName())) {
            currentCustomer.setLastName(newCustomer.getLastName());
        }
        if (newCustomer.getEmail() != null && !newCustomer.getEmail().equals(currentCustomer.getEmail())) {
            currentCustomer.setEmail(newCustomer.getEmail());
        }
        if (newCustomer.getAddress() != null && !newCustomer.getAddress().equals(currentCustomer.getAddress())) {
            currentCustomer.setAddress(newCustomer.getAddress());
        }
        if (newCustomer.getCity() != null && !newCustomer.getCity().equals(currentCustomer.getCity())) {
            currentCustomer.setCity(newCustomer.getCity());
        }
        if (newCustomer.getCountry() != null && !newCustomer.getCountry().equals(currentCustomer.getCountry())) {
            currentCustomer.setCountry(newCustomer.getCountry());
        }
        if (newCustomer.getPhone() != null && !newCustomer.getPhone().equals(currentCustomer.getPhone())) {
            currentCustomer.setPhone(newCustomer.getPhone());
        }
        if (newCustomer.getZip() != null && !newCustomer.getZip().equals(currentCustomer.getZip())) {
            currentCustomer.setZip(newCustomer.getZip());
        }

        return currentCustomer;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer getCustomer(String customerId) {
        return null;
    }

    @Override
    public List<Customer> getCustomers() {
        return List.of();
    }

    @Override
    public void deleteCustomer(String customerId) {

    }

    @Override
    public void updateCustomer(Customer customer) {

    }
}
