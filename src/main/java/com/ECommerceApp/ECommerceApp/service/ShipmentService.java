package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.Category;
import com.ECommerceApp.ECommerceApp.model.Shipment;
import com.ECommerceApp.ECommerceApp.repository.CategoryRepository;
import com.ECommerceApp.ECommerceApp.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;

    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public Iterable<Shipment> findAll() {
        return this.shipmentRepository.findAll();
    }

    public Shipment findById(long id) {
        return this.shipmentRepository.findById(id).orElse(null);
    }

    public void delete(int id) {
        Shipment shipment = this.findById(id);
        this.shipmentRepository.delete(shipment);
    }

    public Shipment save(Shipment shipment) {
        return this.shipmentRepository.save(shipment);
    }
}
