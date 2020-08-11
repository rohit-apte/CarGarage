package com.ing.carapp.service;

import com.ing.carapp.entity.Car;
import com.ing.carapp.entity.Location;
import com.ing.carapp.entity.Vehicle;
import com.ing.carapp.entity.Warehouse;
import com.ing.carapp.repository.WarehouseRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class WarehouseServiceImplTest {

    @InjectMocks
    private WarehouseServiceImpl service;

    @Mock
    private WarehouseRepository repository;

    @Test
    public void findAllVehiclesTest() {
        List<Warehouse> warehouses = getDummyWarehouseDetails();
        Mockito.when(repository.findAll()).thenReturn(warehouses);
        Assert.assertEquals("100", service.findAllVehicles().get(0).getWarehouseId());
        Assert.assertEquals("warehouse1", service.findAllVehicles().get(0).getWarehouseName());
        Assert.assertEquals("2018-09-10", service.findAllVehicles().get(0).getDate_added());
    }

    @Test
    public void findCarByIdTest() {
        List<Warehouse> warehouses = getDummyWarehouseDetails();
        Mockito.when(repository.findWareHouse(Mockito.anyString())).thenReturn(warehouses);
        Assert.assertEquals("fortuner", service.findCarById(100, 5).getModel());
    }

    private List<Warehouse> getDummyWarehouseDetails() {
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle1 = Vehicle.builder()
                .id(5)
                .date_added("2018-09-10")
                .licensed(false)
                .make("Toyota")
                .model("fortuner")
                .price(new BigDecimal("15000"))
                .year_model(2015).build();

        Vehicle vehicle2 = Vehicle.builder()
                .id(6)
                .date_added("2019-12-25")
                .licensed(true)
                .make("Merceden")
                .model("C180")
                .price(new BigDecimal("25000"))
                .year_model(2016).build();

        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        Car cars = Car.builder()
                .location("East")
                .vehicles(vehicles).build();

        List<Warehouse> warehouses = new ArrayList<>();
        Warehouse warehouse = Warehouse.builder()
                .id("100")
                .location(new Location(123324, 12321))
                .cars(cars)
                .name("warehouse1").build();
        warehouses.add(warehouse);
        return warehouses;
    }
}
