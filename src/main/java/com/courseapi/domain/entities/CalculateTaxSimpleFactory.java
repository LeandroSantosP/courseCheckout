package com.courseapi.domain.entities;

import com.courseapi.infra.execptions.InvalidField;

public class CalculateTaxSimpleFactory {

  static CalculateTax create(String location) {

    if (location == "SP") {
      return new CalculateTaxSp();
    }
    if (location == "SC") {
      return new CalculateTaxSc();
    }

    throw new InvalidField("Invalid location: " + location);
  }
}