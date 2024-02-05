package com.courseapi.domain.entities;

public class CalculateTaxSimpleFactory {

  static CalculateTax create(String location) {

    if (location == "SP") {
      return new CalculateTaxSp();
    }
    if (location == "SC") {
      return new CalculateTaxSc();
    }

    throw new RuntimeException("Invalid location: " + location);
  }
}