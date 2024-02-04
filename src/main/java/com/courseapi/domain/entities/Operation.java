package com.courseapi.domain.entities;

import lombok.Getter;
import lombok.NonNull;

public class Operation {
  @Getter
  private @NonNull String value;

  public Operation(String value) {
    if (value != "in" && value != "out") {
      throw new IllegalArgumentException("Invalid Operation");
    }

    this.value = value;
  }

}
