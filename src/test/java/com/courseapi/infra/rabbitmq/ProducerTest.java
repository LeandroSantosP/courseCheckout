package com.courseapi.infra.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
public class ProducerTest {

  @Autowired
  private Producer producer;

  @Test
  void testSendMessage() {
    try {
      this.producer.sendMessage("Hello World");
    } catch (JsonProcessingException e) {
      System.out.println("JsonProcessingException: " + e.getMessage());
      e.printStackTrace();
    } catch (AmqpException e) {
      System.out.println("AmqpException: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
