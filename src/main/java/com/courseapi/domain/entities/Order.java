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

  private double price;

  private String email;

  private String name;

  static public Order create(String courseId, String name, String email, double price) {
    String orderId = UUID.randomUUID().toString();
    String status = "waiting_payment";
    return new Order(orderId, courseId, status, price, email, name);
  }

  public void paymentProcess(String status, String code) {
    if (status == "approved" && code == "00") {
      this.pay();
      return;
    } else if (status == "reject" && code == "01") {
      this.reject();
      return;
    }
    throw new RuntimeException("Invalid payment response!");
  }

  private void pay() {
    this.status = "payed";
  }

  private void reject() {
    this.status = "rejected";
  }

}
