package com.greenfox.demo.model;

import java.util.List;

public class QuareyResult {

  private String result;
  private List<Warehouse> clothes;

  public QuareyResult(String result, List<Warehouse> myList) {
    this.result = result;
    this.clothes = myList;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public List<Warehouse> getClothes() {
    return clothes;
  }

  public void setClothes(List<Warehouse> clothes) {
    this.clothes = clothes;
  }
}
