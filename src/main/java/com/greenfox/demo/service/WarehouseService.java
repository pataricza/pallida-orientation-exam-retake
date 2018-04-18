package com.greenfox.demo.service;

import com.greenfox.demo.model.NewOrder;
import com.greenfox.demo.model.Summary;
import com.greenfox.demo.model.Warehouse;
import com.greenfox.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseService {

  @Autowired
  WarehouseRepository warehouseRepository;

  public List<Warehouse> listAllItems() {
    List<Warehouse> allItems = new ArrayList<>();
    warehouseRepository.findAll()
        .forEach(allItems::add);
    return allItems;
  }

  public List<String> uniqueNames() {
    List<String> uniqueNames = new ArrayList<>();
    warehouseRepository.uniqueNames()
        .forEach(uniqueNames::add);
    return uniqueNames;
  }


  public List<String> uniqueSizes() {
    List<String> uniqueSizes = new ArrayList<>();
    warehouseRepository.uniqueSizes()
        .forEach(uniqueSizes::add);
    return uniqueSizes;
  }

  public Summary calculateSummary(NewOrder newOrder) {
    Summary summary = new Summary();
    List<Warehouse> allItems = listAllItems();
    for (Warehouse oneItem : allItems) {
      if (oneItem.getItemName().equals(newOrder.getName())) {
        summary.setName(oneItem.getItemName());
        summary.setCategory(oneItem.getCategory());
        summary.setManufacturer(oneItem.getManufacturer());
        summary.setQuantity(newOrder.getQuantity());
        summary.setTotal(oneItem.getUnitPrice() * newOrder.getQuantity());
        break;
      }
    }
    return summary;
  }
}
