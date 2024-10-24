package com.ECommerceApp.ECommerceApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORIES")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true, updatable = false)
    private int id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    //@Column(name = "product_id")
    private List<Product> products;

    @Column(name = "NAME", nullable = false, unique = true, updatable = false)
    private String name;
}
