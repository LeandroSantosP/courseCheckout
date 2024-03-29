package com.courseapi.domain.entities;

import com.courseapi.infra.execptions.InvalidField;

public class StokeEntryFactory {

  public static StokeEntry create(String operation, String productId, int amount) {
    if (operation.equals("in")) {
      return StokeEntryIn.create(productId, amount);
    }

    if (operation.equals("out")) {
      return StokeEntryOut.create(productId, amount);
    }

    throw new InvalidField("Invalid operation: " + operation);
  }

  public static StokeEntry rebuild(String operation, String id, String productId, int amount) {
    if (operation.equals("in")) {
      return new StokeEntryIn(id, productId, amount);
    }
    if (operation.equals("out")) {
      return new StokeEntryOut(id, productId, amount);
    }

    throw new InvalidField("Invalid operation: " + operation);
  }

}
