package com.ECommerceApp.ECommerceApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    //@JsonIgnore
    //TODO it returns recursively
    private List<Product> products;

    @Column(name = "NAME", nullable = false, unique = true, updatable = false)
    private String name;
}
