package com.ECommerceApp.ECommerceApp.repository;

import com.ECommerceApp.ECommerceApp.model.Category;
import com.ECommerceApp.ECommerceApp.model.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Product findById(int id);
    Product findByDescription(String description);
    Product findBySku(String sku);
    Product findByCategory(Category category);

}
