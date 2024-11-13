package com.ECommerceApp.ECommerceApp.controller;

import com.ECommerceApp.ECommerceApp.dto.AuthResponseDTO;
import com.ECommerceApp.ECommerceApp.dto.CustomerDto;
import com.ECommerceApp.ECommerceApp.dto.LoginDTO;
import com.ECommerceApp.ECommerceApp.model.Cart;
import com.ECommerceApp.ECommerceApp.model.Customer;
import com.ECommerceApp.ECommerceApp.model.UserRole;
import com.ECommerceApp.ECommerceApp.repository.CustomerRepository;
import com.ECommerceApp.ECommerceApp.repository.RoleRepository;
import com.ECommerceApp.ECommerceApp.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator generator;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder, CustomerRepository customerRepository, JWTGenerator generator) {
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
        this.generator = generator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        //produce an auth obj
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = generator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody CustomerDto customerDTO) {
        if(customerRepository.existsByEmail(customerDTO.getEmail()))
            return new ResponseEntity<>("This email is already used", HttpStatus.BAD_REQUEST);

        Customer customer = customerDTO.toCustomer();
        //TODO: password too long
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        //com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'password' at row 1

        UserRole role = roleRepository.findByName("CUSTOMER").get();
        customer.setUserRoles(Collections.singletonList(role));

        Cart cart = new Cart();
        cart.setCustomer(customer);
        customer.setCart(cart);

        customerRepository.save(customer);
        return new ResponseEntity<>("Customer registered successfully", HttpStatus.OK);

    }
}
