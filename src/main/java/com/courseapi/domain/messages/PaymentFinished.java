package com.courseapi.domain.messages;

public class PaymentFinished extends Message<PaymentFinashedMessage> {

  public PaymentFinished() {
    super("payment-finished", null);
  }

  public PaymentFinished(String orderId, String status, String code) {
    super("payment-finished", new PaymentFinashedMessage(orderId, status, code));
  }

  public PaymentFinished(String orderId, String status, String code, String exchange, String routekey) {
    super("payment-finished", new PaymentFinashedMessage(orderId, status, code), exchange, routekey);
  }

  @Override
  protected PaymentFinashedMessage encryptMessage(PaymentFinashedMessage message) {
    return message;
  }

  @Override
  protected PaymentFinashedMessage decryptMessage(PaymentFinashedMessage message) {
    return message;
  }

}