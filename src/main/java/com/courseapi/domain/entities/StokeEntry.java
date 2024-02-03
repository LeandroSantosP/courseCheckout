package com.courseapi.domain.entities;

import lombok.Getter;

@Getter
public abstract class StokeEntry {

  private String productId;
  protected int amount;
  private Operation operation;

  public StokeEntry(String productId, int amount, String operation) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }
    this.productId = productId;
    this.amount = amount;
    this.operation = new Operation(operation);
  }

  public String getOperation() {
    return this.operation.getValue();
  }

  abstract public int calc(int total);

}
