package com.courseapi.application.usecases.CheckoutBoundContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseapi.application.interfaces.ConfirmOrderRepo;
import com.courseapi.application.interfaces.MessageHandler;
import com.courseapi.domain.entities.Order;
import com.courseapi.domain.messages.PaymentFinashedMessage;

@Service
public class ConfimOrder implements MessageHandler<PaymentFinashedMessage> {

  @Autowired
  private ConfirmOrderRepo orderRepository;

  @Override
  public void handle(PaymentFinashedMessage input) {
    Order order = this.orderRepository.get(input.orderId());
    order.paymentProcess(input.status(), input.code());
    this.orderRepository.update(order);
  }

}

/**
 * InnerConfimOrder
 */
