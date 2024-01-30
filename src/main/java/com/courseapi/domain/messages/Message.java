package com.courseapi.domain.messages;

import lombok.Getter;

@Getter
public abstract class Message<T> {
  protected String queueName;
  protected String exchange;
  protected String routingKey;
  protected T message;

  public Message(String queueName, T message) {
    this.queueName = queueName;
    this.message = this.encryptMessage(message);
  }

  public Message(String queueName, T message, String exchange, String routingKey) {
    this.queueName = queueName;
    this.exchange = exchange;
    this.routingKey = routingKey;
    this.message = this.encryptMessage(message);
  }

  protected abstract T encryptMessage(T message);

  protected abstract T decryptMessage(T message);

  public T getMessage() {
    return decryptMessage(this.message);
  }
}
