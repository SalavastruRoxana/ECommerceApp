package com.ECommerceApp.ECommerceApp.repository;

import com.ECommerceApp.ECommerceApp.model.Shipment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ShipmentRepository extends CrudRepository<Shipment, Long>, JpaSpecificationExecutor<Shipment> {
}