package com.courseapi.domain.messages;

import lombok.Getter;

@Getter
public class OrderCreate extends Message<OrderMessage> {

  public OrderCreate() {
    super("order-create", null);
  }

  public OrderCreate(String orderId, double price, String creditCardToken) {
    super("order-create", new OrderMessage(orderId, price, creditCardToken));
  }

  public OrderCreate(String orderId, double price, String creditCardToken, String exchange, String routekey) {
    super("order-create", new OrderMessage(orderId, price, creditCardToken), exchange, routekey);
  }

  @Override
  protected OrderMessage encryptMessage(OrderMessage message) {
    return message;
  }

  @Override
  protected OrderMessage decryptMessage(OrderMessage message) {
    return message;
  }

}
