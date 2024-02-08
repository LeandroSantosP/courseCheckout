package com.courseapi.domain.entities;

import com.courseapi.infra.execptions.InvalidField;

import lombok.Getter;

@Getter
public abstract class StokeEntry {

  private String id;
  private String productId;
  protected int amount;
  private Operation operation;

  public StokeEntry(String id, String productId, int amount, String operation) {

    if (amount <= 0) {
      throw new InvalidField("Amount cannot be negative");
    }
    this.id = id;
    this.productId = productId;
    this.amount = amount;
    this.operation = new Operation(operation);
  }

  public String getOperation() {
    return this.operation.getValue();
  }

  abstract public int calc(int total);

}
