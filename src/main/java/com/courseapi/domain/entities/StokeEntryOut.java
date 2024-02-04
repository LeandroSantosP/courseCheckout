package com.courseapi.domain.entities;

import java.util.UUID;

public class StokeEntryOut extends StokeEntry {

  public StokeEntryOut(String id, String productId, int amount) {
    super(id, productId, amount, "out");
  }

  static public StokeEntryOut create(String productId, int amount) {
    String initialId = UUID.randomUUID().toString();
    return new StokeEntryOut(initialId, productId, amount);
  }

  @Override
  public int calc(int total) {
    return total -= this.amount;
  }

}