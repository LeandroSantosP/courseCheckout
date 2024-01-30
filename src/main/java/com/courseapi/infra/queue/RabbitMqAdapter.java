package com.courseapi.infra.queue;

import java.util.function.Consumer;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.courseapi.application.usecases.CheckoutBoundContext.ConfimOrder;
import com.courseapi.application.usecases.paymentBoundContext.PaymentProcess;
import com.courseapi.domain.messages.Message;
import com.courseapi.domain.messages.OrderCreate;
import com.courseapi.domain.messages.PaymentFinished;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RabbitMqAdapter implements QueueBroken {

  @Autowired
  private AmqpTemplate amqpTemplate;

  @Autowired
  PaymentProcess paymentProcess;

  @Autowired
  ConfimOrder confimOrder;

  @Override
  public void publisher(Message<?> message) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      this.amqpTemplate.convertAndSend(
          message.getExchange(),
          message.getRoutingKey(),
          objectMapper.writeValueAsString(message));

    } catch (JsonProcessingException | AmqpException e) {
      e.printStackTrace();
      System.out.println("JsonProcessingException: " + e.getMessage());
      System.out.println("AmqpException: " + e.getMessage());
    }
  }

  @RabbitListener(queues = "order-create")
  void onMessage(String input) {
    try {
      OrderCreate message = new ObjectMapper().readValue(input.toString(),
          OrderCreate.class);
      this.paymentProcess.handle(message.getMessage());
    } catch (JsonProcessingException e) {
      System.out.println("JsonProcessingException: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @RabbitListener(queues = "payment-finished")
  void onPaymentFinashed(String input) {
    try {
      PaymentFinished message = new ObjectMapper().readValue(input.toString(),
          PaymentFinished.class);
      confimOrder.handle(message.getMessage());
    } catch (JsonProcessingException e) {
      System.out.println("JsonProcessingException: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public void consumer(String queueName, Consumer<Message<?>> call) {
    // Object input = this.amqpTemplate.receiveAndConvert(queueName);
    // try {
    // if (input != null) {
    // Message<?> message = new ObjectMapper().readValue(input.toString(),
    // OrderCreateMessage.class);
    // call.accept(message);
    // } else {
    // System.out.println("No messages in the queue.");
    // }
    // } catch (JsonProcessingException e) {
    // System.out.println("JsonProcessingException: " + e.getMessage());
    // e.printStackTrace();
    // }
  }

}
