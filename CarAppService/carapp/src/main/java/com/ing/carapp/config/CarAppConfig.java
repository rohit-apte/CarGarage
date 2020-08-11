package com.ing.carapp.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.carapp.entity.Warehouse;
import com.ing.carapp.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CarAppConfig {

    @Autowired
    private WarehouseService warehouseService;
    private static Logger LOG = LoggerFactory.getLogger("CarAppConfig");

    @Bean
    public List<Warehouse> saveWarehouseDetails() {
        List<Warehouse> warehouses = new ArrayList<>();
        try {
            String data = Files.readString(Path.of("src/main/resources/CarData.json"));
            ObjectMapper mapper = new ObjectMapper();
            List<Warehouse> warehouse = mapper.readValue(data, new TypeReference<List<Warehouse>>() {
            });
            warehouses = warehouseService.save(warehouse);
        } catch (FileNotFoundException e) {
            LOG.error("Car Data File not found at given path.");
        } catch (IOException e) {
            LOG.error("Unable to load data from file");
        }
        return warehouses;
    }
}
