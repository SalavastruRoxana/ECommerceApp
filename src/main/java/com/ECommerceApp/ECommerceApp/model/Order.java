package com.ECommerceApp.ECommerceApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table (name = "ORDERS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true, updatable = false)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)// not to load all orders when I want to get only the customer
    @JoinColumn(name = "customer_id", referencedColumnName = "id") //maybe without referenced column
    private Customer customer;

    @Column(name = "DATE", unique = true, updatable = false)
    private Date date;

    @Column(name = "PRICE", nullable = false, unique = true, updatable = false)
    private int price;

}
