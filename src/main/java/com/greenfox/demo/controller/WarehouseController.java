package com.greenfox.demo.controller;

import com.greenfox.demo.model.NewOrder;
import com.greenfox.demo.model.Summary;
import com.greenfox.demo.model.Warehouse;
import com.greenfox.demo.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WarehouseController {

  @Autowired
  WarehouseService warehouseService;

  @GetMapping(value = "/warehouse")
  public String warehouse(Model model) {
    List<Warehouse> allItems = warehouseService.listAllItems();
    List<String> uniqueNames = warehouseService.uniqueNames();
    List<String> uniqueSizes = warehouseService.uniqueSizes();

    model.addAttribute("items", allItems);
    model.addAttribute("uniquenames", uniqueNames);
    model.addAttribute("uniquesizes", uniqueSizes);
    model.addAttribute("order", new NewOrder());
    return "index";
  }

  @PostMapping(value = "/warehouse/summary")
  public String getNewOrder(@ModelAttribute NewOrder order, Model model) {
    Summary summary = warehouseService.calculateSummary(order);
    model.addAttribute("summary", summary);
    return "summary";
  }
}
