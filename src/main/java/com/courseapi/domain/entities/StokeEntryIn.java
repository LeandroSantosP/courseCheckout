package com.courseapi.domain.entities;

import java.util.UUID;

public class StokeEntryIn extends StokeEntry {

  public StokeEntryIn(String id, String productId, int amount) {

    super(id, productId, amount, "in");
  }

  static public StokeEntryIn create(String productId, int amount) {
    String initialId = UUID.randomUUID().toString();
    return new StokeEntryIn(initialId, productId, amount);
  }

  @Override
  public int calc(int total) {
    return total += this.amount;
  }

}