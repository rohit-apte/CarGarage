package com.ing.carapp.controller;

import com.ing.carapp.entity.Warehouse;
import com.ing.carapp.model.CarDetails;
import com.ing.carapp.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Controller class exposing various end points for Car Application
 */
@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService service;


    // Endpoint for viewing list of  all cars available across all the warehouses
    @GetMapping("/cars")
    public List<CarDetails> findAllVehicles() {
        return service.findAllVehicles();
    }

    // Endpoint for fetching car details for given warehouseId and carId
    @GetMapping("/car/{warehouseId}/{carId}")
    public CarDetails findCar(@PathVariable int warehouseId, @PathVariable int carId) {
        return service.findCarById(warehouseId, carId);
    }

    // Endpoint for placing order from cart
    @PostMapping(value = "/order", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Map> placeOrder(@RequestBody String map) {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("msg", "success");
        stringMap.put("transactionID", UUID.randomUUID().toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(stringMap);
    }

}
