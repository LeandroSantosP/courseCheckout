package com.courseapi.infra.queue;

import java.util.function.Consumer;

import com.courseapi.domain.messages.Message;

public interface QueueBroken {
  public void publisher(Message<?> message);

}
