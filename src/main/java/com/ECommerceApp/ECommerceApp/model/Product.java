package com.ECommerceApp.ECommerceApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "PRODUCTS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true, updatable = false)
    private int id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "order_products", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private List<Order> order;

//    //or many to one???
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
   private CartContent cartContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "sku", nullable = false, length = 50, updatable = true)
    private String sku;

    @Column(name = "description", nullable = false, length = 50, updatable = true)
    private String description;

    @Column(name = "price", nullable = false, length = 50, updatable = true)
    private Float price;

    @Column(name = "stock", nullable = false, length = 50, updatable = true)
    private Integer stock;

}
