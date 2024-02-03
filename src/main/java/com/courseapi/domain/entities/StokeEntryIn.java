package com.courseapi.domain.entities;

public class StokeEntryIn extends StokeEntry {

  public StokeEntryIn(String productId, int amount) {
    super(productId, amount, "in");
  }

  @Override
  public int calc(int total) {
    return total += this.amount;
  }

}