package com.courseapi.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.courseapi.application.gateway.PaymentGateway;
import com.courseapi.application.gateway.PaymentGateway.Input;
import com.courseapi.application.queue.MessageHandler;
import com.courseapi.application.repositories.OrderRepository;
import com.courseapi.domain.entities.Order;
import com.courseapi.domain.messages.OrderCreateMessage;

@Service
public class PaymentProcess implements MessageHandler<OrderCreateMessage> {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private PaymentGateway paymentGateway;

  @Override
  public void handle(OrderCreateMessage input) {
    var message = input.getMessage();
    Order order = this.orderRepository.get(message.orderId());
    var response = this.paymentGateway
        .processePayment(new Input(message.orderId(), message.creditCardToken(),
            message.price()));
    order.paymentProcess(response.status(), response.code());
    this.orderRepository.update(order);
  }

}
