package com.courseapi.infra.gateways;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.courseapi.application.interfaces.PaymentGateway;

@Primary
@Component
public class PaymentGatewayFack implements PaymentGateway {

  @Override
  public Output processePayment(Input input) {
    return new Output("approved", "12345678", "00",
        "The transaction was processed succefully, and the payment was accepted!");
  }

}
