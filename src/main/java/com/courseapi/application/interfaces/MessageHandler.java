package com.courseapi.application.interfaces;

public interface MessageHandler<T> {
  void handle(T input);
}
