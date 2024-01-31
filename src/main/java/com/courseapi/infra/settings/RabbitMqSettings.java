package com.courseapi.infra.settings;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqSettings {

  @Bean
  public DirectExchange directExchangeOrderCreate() {
    return new DirectExchange("order-create-exchange");
  }

  @Bean
  public DirectExchange directExchangePaymentFinished() {
    return new DirectExchange("payment-finished-exchange");
  }

  @Bean
  public Queue orderCreateQueue() {
    return QueueBuilder.durable("order-create").build();
  }

  @Bean
  public Queue paymentFinishedQueue() {
    return QueueBuilder.durable("payment-finished").build();
  }

  @Bean
  public Binding bindingOrderCreate(Queue orderCreateQueue,
      @Qualifier("directExchangeOrderCreate") DirectExchange directExchangeOrderCreate) {
    return BindingBuilder.bind(orderCreateQueue).to(directExchangeOrderCreate).with("order-create-route-key");
  }

  @Bean
  public Binding bindingPaymentFinished(Queue paymentFinishedQueue,
      @Qualifier("directExchangePaymentFinished") DirectExchange directExchangePaymentFinished) {
    return BindingBuilder.bind(paymentFinishedQueue).to(directExchangePaymentFinished)
        .with("payment-finished-route-key");
  }
}
