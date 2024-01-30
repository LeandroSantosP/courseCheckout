package com.courseapi.application.interfaces;

public interface PaymentGateway {

  public Output processePayment(Input input);

  public record Input(String orderId, String creditCardToken, double price) {
  }

  public record Output(String status, String tid, String code, String desc) {
  }

}
