package com.ECommerceApp.ECommerceApp.security;

import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    //@Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return new User(customer.getEmail(), customer.getPassword(), customer.getGrantedAuthorityRoles());
    }
}
