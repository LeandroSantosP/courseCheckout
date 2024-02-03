package com.courseapi.domain.entities;

public class StokeEntryOut extends StokeEntry {

  public StokeEntryOut(String productId, int amount) {
    super(productId, amount, "out");
  }

  @Override
  public int calc(int total) {
    return total -= this.amount;
  }

}