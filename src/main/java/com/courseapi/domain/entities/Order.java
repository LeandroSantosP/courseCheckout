package com.courseapi.domain.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Order {

  private final String id;

  private final String courseId;

  private String status;

  private int amount;

  private String email;

  private String name;

  static public Order create(String courseId, String name, String email, int amount) {
    return new Order(UUID.randomUUID().toString(), courseId, "wayting_payment", amount, email, name);
  }

}
