package com.courseapi.domain.entities;

public class CalculateTaxSc implements CalculateTax {

  private double iof_percentage = 2.0;

  @Override
  public double calculate(double price) {
    double iof = (price * this.iof_percentage) / 100.0;
    return iof;
  }

}
