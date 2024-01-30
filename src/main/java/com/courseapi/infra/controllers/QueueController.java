package com.courseapi.infra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.courseapi.infra.queue.QueueBroken;

@Controller
public class QueueController {

  public QueueController(@Autowired QueueBroken queue) {
    // System.out.println("LEANDRO222");
    // try {
    // queue.consumer("order-create", (message) -> {
    // if (!(message instanceof OrderCreateMessage)) {
    // System.out.println("Invalid Message for order-create queue");
    // return;
    // }
    // OrderCreateMessage orderCreateMessage = (OrderCreateMessage) message;
    // paymentProcess.handle(orderCreateMessage);
    // });
    // } catch (Exception e) {
    // System.out.println("ERRO ON QUEUE CONTR0LLER: " + e.getMessage());
    // }

  }
}
