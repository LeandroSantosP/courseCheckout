package com.courseapi.infra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.courseapi.application.queue.QueueBroken;
import com.courseapi.application.usecases.PaymentProcess;
import com.courseapi.domain.messages.OrderCreateMessage;

@Controller
public class QueueController {

  @Autowired
  PaymentProcess paymentProcess;

  public QueueController(@Autowired QueueBroken queue) {
    try {
      queue.consumer("order-create", (message) -> {
        if (!(message instanceof OrderCreateMessage)) {
          System.out.println("Invalid Message for order-create queue");
          return;
        }
        OrderCreateMessage orderCreateMessage = (OrderCreateMessage) message;
        paymentProcess.handle(orderCreateMessage);
      });
    } catch (Exception e) {
      System.out.println("ERRO ON QUEUE CONTR0LLER: " + e.getMessage());
    }

  }
}
