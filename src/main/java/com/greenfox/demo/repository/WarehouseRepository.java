package com.greenfox.demo.repository;

import com.greenfox.demo.model.Warehouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WarehouseRepository extends CrudRepository<Warehouse, Long>{

  @Query("select distinct w.itemName from Warehouse w")
  List<String> uniqueNames();

  @Query("select distinct w.size from Warehouse w")
  List<String> uniqueSizes();

  List<Warehouse> findAllByUnitPriceEquals(float price);
  List<Warehouse> findAllByUnitPriceIsLessThan(float price);
  List<Warehouse> findAllByUnitPriceGreaterThan(float price);
}
