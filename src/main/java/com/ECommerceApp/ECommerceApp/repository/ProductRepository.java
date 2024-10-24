package com.ECommerceApp.ECommerceApp.repository;

import com.ECommerceApp.ECommerceApp.model.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Product findById(int id);
}
