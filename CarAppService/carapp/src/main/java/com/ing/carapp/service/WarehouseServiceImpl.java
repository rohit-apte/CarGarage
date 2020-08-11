package com.ing.carapp.service;

import com.ing.carapp.entity.Vehicle;
import com.ing.carapp.entity.Warehouse;
import com.ing.carapp.model.CarDetails;
import com.ing.carapp.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Class for Car Application
 * This class contains the Business logic for Car Application
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseRepository repository;

    @Override
    public List<Warehouse> save(List<Warehouse> warehouses) {
        return repository.saveAll(warehouses);
    }


    /**
     * Finds a list of all the cars available accross alll the warehouses
     *
     * @return List of car and warehouse details
     */
    @Override
    public List<CarDetails> findAllVehicles() {
        // fetching details of all warehouses along with car from Database
        List<Warehouse> warehouses = repository.findAll();
        List<CarDetails> carDetailsList = new ArrayList<>();
        warehouses.forEach(warehouse -> {
            warehouse.getCars().getVehicles().forEach(vehicle ->
            {
                // Setting CarDetails object from vehicle and warehouse details
                CarDetails carDetails = CarDetails.builder()
                        .warehouseId(warehouse.getId())
                        .warehouseName(warehouse.getName())
                        .location(warehouse.getCars().getLocation())
                        .carId(vehicle.getId())
                        .make(vehicle.getMake())
                        .model(vehicle.getModel())
                        .year_model(vehicle.getYear_model())
                        .price(vehicle.getPrice())
                        .licensed(vehicle.isLicensed())
                        .date_added(vehicle.getDate_added()).build();
                // adding CarDetails object in List
                carDetailsList.add(carDetails);
            });
        });

        // List of CarDetails needs to sorted by Date Added ascending
        carDetailsList.sort((o1, o2) -> {
            LocalDate localDate1 = LocalDate.parse(o1.getDate_added());
            LocalDate localDate2 = LocalDate.parse(o2.getDate_added());
            if (localDate1.isBefore(localDate2)) {
                return -1;
            } else if (localDate1.isAfter(localDate2)) {
                return 1;
            }
            return 0;
        });
        return carDetailsList;
    }


    /**
     * Finds a car by warehouseId and carId
     *
     * @param warehouseId - Id for warehouse
     * @param carId       - Id for selected car
     * @return - CarDetails if a car if found by given details, null otherwise
     */
    @Override
    public CarDetails findCarById(int warehouseId, int carId) {
        // fetching details of warehouse by warehouseId along with car from Database
        List<Warehouse> warehouses = repository.findWareHouse(warehouseId + "");
        Optional<Vehicle> vehicle;
        CarDetails carDetails = null;
        if (!CollectionUtils.isEmpty(warehouses)) {
            // Setting CarDetails object from vehicle and warehouse details
            vehicle = warehouses.get(0).getCars().getVehicles().stream().filter(veh -> veh.getId() == carId).findFirst();
            if (vehicle.isPresent()) {
                carDetails = CarDetails.builder()
                        .warehouseId(warehouseId + "")
                        .warehouseName(warehouses.get(0).getName())
                        .latitude(warehouses.get(0).getLocation().getLatitude())
                        .longitude(warehouses.get(0).getLocation().getLongitude())
                        .location(warehouses.get(0).getCars().getLocation())
                        .carId(vehicle.get().getId())
                        .make(vehicle.get().getMake())
                        .model(vehicle.get().getModel())
                        .year_model(vehicle.get().getYear_model())
                        .price(vehicle.get().getPrice())
                        .licensed(vehicle.get().isLicensed())
                        .date_added(vehicle.get().getDate_added()).build();
            }
        }
        return carDetails;
    }

}
