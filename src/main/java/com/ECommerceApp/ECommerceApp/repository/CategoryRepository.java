package com.ECommerceApp.ECommerceApp.repository;

import com.ECommerceApp.ECommerceApp.model.Category;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    Category findById(int id);
}