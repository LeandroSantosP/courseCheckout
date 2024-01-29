package com.courseapi.infra.rabbitmq;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/* This class is used to receive messages and a real context it should stay in other microservices */
@Component
public class Reciver {

  @RabbitListener(queues = { "greating-request" })
  public void receiveMessage(String message) {
    System.out.println("Received <" + message + ">");
  }
}
