package com.ECommerceApp.ECommerceApp.controller;

import com.ECommerceApp.ECommerceApp.model.Shipment;
import com.ECommerceApp.ECommerceApp.service.ShipmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("api/")
public class ShipmentController {
    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("/customer/{cusId}/shipment")
    public Shipment createShipment(@RequestBody Shipment shipment) {
        return shipmentService.save(shipment);
    }

    @GetMapping("/customer/{cusId}/shipments")
    public Iterable<Shipment> getAllShipments(@PathVariable Long cusId) {
        return shipmentService.findAll();
    }
}
