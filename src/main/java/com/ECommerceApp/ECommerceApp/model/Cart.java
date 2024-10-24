package com.ECommerceApp.ECommerceApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CARTS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, updatable = false)
    private int id;

//    @OneToMany(fetch = FetchType.LAZY) //delete from here mappedBy
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    private List<Product> products;

    @JsonIgnore // without this I would get recursively the customer with his attributes including cart and so on
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id") //or maybe new customer_id
    private Customer customer;

    @Column(name = "QUANTITY")
    private int quantity;
}
