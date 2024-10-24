package com.ECommerceApp.ECommerceApp.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@Table (name = "CUSTOMERS")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    @Column(name = "ID", nullable = false, unique = true, updatable = false )
    private int id;

    //@JsonIgnore // might be needed if infinite recursion happens
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Shipment> shipments;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

    @Column(name = "FirstName", nullable = false, length = 50, updatable = true)
    private String firstName;

    @Column(name = "LastName", nullable = false, length = 50, updatable = true)
    private String lastName;

    @Column(name = "Email", nullable = false, length = 50, updatable = true)
    private String email;

    @Column(name = "Phone", nullable = false, length = 50, updatable = true)
    private String phone;

    @Column(name = "Address", nullable = false, length = 50, updatable = true)
    private String address;

    @Column(name = "City", nullable = false, length = 50, updatable = true)
    private String city;

    @Column(name = "State", nullable = false, length = 50, updatable = true)
    private String state;

    @Column(name = "Zip", nullable = false, length = 50, updatable = true)
    private String zip;

    @Column(name = "Country", nullable = false, length = 50, updatable = true)
    private String country;

    @Column(name = "Password", nullable = false, length = 200, updatable = true)
    private String password;

    public Customer( String firstName, String lastName, String email, String password, String phone, String address, String city, String state, String zip, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.password = password;
    }

    public Collection<GrantedAuthority> getGrantedAuthorityRoles() {
        return this.userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
