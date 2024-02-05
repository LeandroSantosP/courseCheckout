package com.courseapi.integration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import com.courseapi.application.interfaces.PaymentGateway;
import com.courseapi.application.usecases.paymentBoundContext.PaymentProcess;
import com.courseapi.domain.messages.Message;
import com.courseapi.domain.messages.OrderMessage;
import com.courseapi.infra.gateways.PaymentGatewayFack;
import com.courseapi.infra.queue.QueueBroken;

public class PaymentProcessTest {

  @Test
  void shouldBeAbleMakeAPayment() {
    final var queueBroken = new QueueBroken() {
      final ArrayList<Message<?>> messages = new ArrayList<>();

      @Override
      public void publisher(Message<?> message) {
        messages.add(message);
      }

      public void consumer(String queueName) {
        this.messages.stream().forEach(mess -> {
          if (mess.getQueueName().equals(queueName)) {
            System.out.println("Message received: " + mess.getMessage());
            assertNotNull(mess);
          }
        });
      }
    };

    PaymentGateway paymentGateway = new PaymentGatewayFack();

    PaymentProcess paymentProcess = new PaymentProcess(paymentGateway, queueBroken);
    assertDoesNotThrow(() -> {
      paymentProcess.handle(new OrderMessage("123", 100.00, "123456789", "1223"));
      queueBroken.consumer("payment-finished");
    });
  }

}
