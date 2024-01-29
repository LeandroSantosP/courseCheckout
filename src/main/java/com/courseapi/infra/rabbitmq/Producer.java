package com.courseapi.infra.rabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Producer {

  @Autowired
  private AmqpTemplate amqpTemplate;

  public void sendMessage(String greating) throws JsonProcessingException, AmqpException {
    ObjectMapper objectMapper = new ObjectMapper();
    this.amqpTemplate.convertAndSend(
        "greating-request-exchange",
        "greating-request-route-key",
        objectMapper.writeValueAsString("Producer: " + greating));
  }

}
