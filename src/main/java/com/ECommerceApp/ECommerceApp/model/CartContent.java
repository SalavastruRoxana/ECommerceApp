package com.ECommerceApp.ECommerceApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "CART_CONTENT")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true, updatable = false)
    private int id;

    @OneToMany(mappedBy = "cartContent")
    private List<Product> products;



}
