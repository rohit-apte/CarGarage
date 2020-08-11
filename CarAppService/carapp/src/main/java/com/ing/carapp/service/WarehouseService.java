package com.ing.carapp.service;

import com.ing.carapp.entity.Warehouse;
import com.ing.carapp.model.CarDetails;

import java.util.List;

public interface WarehouseService {

    List<Warehouse> save(List<Warehouse> warehouses);

    List<CarDetails> findAllVehicles();

    CarDetails findCarById(int warehouseId, int carId);
}
