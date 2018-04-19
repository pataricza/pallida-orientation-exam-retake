package com.greenfox.demo.controller;

import com.greenfox.demo.model.QuareyResult;
import com.greenfox.demo.model.Warehouse;
import com.greenfox.demo.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WarehouseRestController {

  @Autowired
  WarehouseService warehouseService;

  @GetMapping(value = "/warehouse/query")
  public ResponseEntity<QuareyResult> quareyTheItems(@RequestParam("price") int price, @RequestParam("type") String type) {
    List<Warehouse> result = warehouseService.findItemsByParam(type, price);
    QuareyResult qResult = new QuareyResult("ok", result);
    return ResponseEntity.status(HttpStatus.OK).body(qResult);
  }
}
