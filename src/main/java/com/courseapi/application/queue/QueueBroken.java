package com.courseapi.application.queue;

import java.util.function.Consumer;

import com.courseapi.domain.messages.Message;

public interface QueueBroken {
  public void publisher(Message<?> message);

  public void consumer(String queueName, Consumer<Message<?>> call);

}
