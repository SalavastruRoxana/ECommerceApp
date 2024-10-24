package com.ECommerceApp.ECommerceApp.repository;

import com.ECommerceApp.ECommerceApp.model.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    Customer findById(int id);
    Boolean existsByEmail(String email);
    Optional<Customer> findByEmail(String email);

    //Customer findByCredentials(String customerUsername, String customerPassword);
}