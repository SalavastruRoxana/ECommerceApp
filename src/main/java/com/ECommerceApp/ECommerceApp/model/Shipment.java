package com.ECommerceApp.ECommerceApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SHIPMENTS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true, updatable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "address", nullable = false, length = 50, updatable = true)
    private String address;

    @Column(name = "date", nullable = false, length = 50, updatable = true)
    private Date date;

    @Column(name = "city", nullable = false, length = 50, updatable = true)
    private String city;

    @Column(name = "state", nullable = false, length = 50, updatable = true)
    private String state;

    @Column(name = "country", nullable = false, length = 50, updatable = true)
    private String country;

    @Column(name = "zipCode", nullable = false, length = 50, updatable = true)
    private String zipCode;

}
