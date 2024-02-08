package com.courseapi.domain.entities;

import java.util.UUID;

import com.courseapi.infra.execptions.CustomError;

import lombok.Getter;

@Getter
public class Order {

  private final String id;

  private final String courseId;

  private String status;

  private double price;

  private Email email;

  private String name;

  public Order(String id, String courseId, String status, double price, String email, String name) {
    this.id = id;
    this.courseId = courseId;
    this.status = status;
    this.price = price;
    this.email = new Email(email);
    this.name = name;
  }

  static public Order create(String courseId, String name, String email, double price) {

    String orderId = UUID.randomUUID().toString();
    String status = "waiting_payment";
    return new Order(orderId, courseId, status, price, email, name);
  }

  public static Order rebuild(String orderId, String status, String courseId, String name, String email, double price) {

    return new Order(orderId, courseId, status, price, email, name);
  }

  public void paymentProcess(String status, String code) {

    if (status.equals("approved") && code.equals("00")) {
      this.pay();
      return;
    } else if (status.equals("reject") && code.equals("01")) {
      this.reject();
      return;
    }
    throw new CustomError("Invalid payment response!");
  }

  private void pay() {
    this.status = "payed";
  }

  private void reject() {
    this.status = "rejected";
  }

  public String getEmail() {
    return this.email.getValue();
  }

  public double getTotal(String location) {
    double iof = CalculateTaxSimpleFactory.create(location).calculate(this.price);
    double total = this.price + iof;
    return total;
  }

}
