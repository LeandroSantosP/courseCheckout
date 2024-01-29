package com.courseapi.application.queue;

import com.courseapi.domain.messages.Message;

public interface MessageHandler<T extends Message<?>> {
  void handle(T input);
}
