package com.ECommerceApp.ECommerceApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PAYMENTS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true, updatable = false)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "payment")
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order = new Order();

    @Column(name = "payment_date", nullable = false,  updatable = true)
    private Date paymentDate;

    @Column(name = "payment_method", nullable = false, length = 50, updatable = true)
    private String paymentMethod;

    @Column(name = "amount", nullable = false, length = 50, updatable = true)
    private Float amount;
}
