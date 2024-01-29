package com.courseapi.domain.messages;

import lombok.Getter;

@Getter
public class OrderCreateMessage extends Message<OrderMessage> {

  public OrderCreateMessage() {
    super("orderCreateMessage", null);
  }

  public OrderCreateMessage(String orderId, double price, String creditCardToken) {
    super("orderCreateMessage", new OrderMessage(orderId, price, creditCardToken));
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
