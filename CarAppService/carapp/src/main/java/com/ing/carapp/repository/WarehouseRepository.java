package com.ing.carapp.repository;

import com.ing.carapp.entity.Warehouse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends MongoRepository<Warehouse, Integer> {

    /**
     * Find warehouse from Database and returns complete details of warehouses.
     *
     * @param warehouseId - Id for Warehouse
     * @return - Returns the list of  all the warehouses matching with given Id
     */
    @Query(value = "{'id':?0}")
    List<Warehouse> findWareHouse(String warehouseId);

}
