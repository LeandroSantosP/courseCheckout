package com.courseapi.infra.queue;

import com.courseapi.domain.messages.Message;

public interface QueueBroken {
  public void publisher(Message<?> message);

}
