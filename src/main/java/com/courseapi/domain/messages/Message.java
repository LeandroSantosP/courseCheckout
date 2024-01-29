package com.courseapi.domain.messages;

import lombok.Getter;

@Getter
public abstract class Message<T> {
  protected String name;
  protected T message;

  public Message(String name, T message) {
    this.name = name;
    this.message = this.encryptMessage(message);
  }

  protected abstract T encryptMessage(T message);

  protected abstract T decryptMessage(T message);

  public T getMessage() {
    return decryptMessage(this.message);
  }
}
