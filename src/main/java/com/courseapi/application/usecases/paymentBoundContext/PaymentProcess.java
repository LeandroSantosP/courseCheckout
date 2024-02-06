package com.courseapi.application.usecases.paymentBoundContext;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.courseapi.application.interfaces.MessageHandler;
import com.courseapi.application.interfaces.PaymentGateway;
import com.courseapi.application.interfaces.PaymentGateway.Input;
import com.courseapi.domain.messages.OrderMessage;
import com.courseapi.domain.messages.PaymentFinished;
import com.courseapi.infra.queue.QueueBroken;

@Service
public class PaymentProcess implements MessageHandler<OrderMessage> {

  private PaymentGateway paymentGateway;

  private QueueBroken queueBroken;

  public PaymentProcess(PaymentGateway paymentGateway, @Lazy QueueBroken queueBroken) {
    this.paymentGateway = paymentGateway;
    this.queueBroken = queueBroken;
  }

  @Override
  public void handle(OrderMessage message) {
    var response = this.paymentGateway.processePayment(new Input(message.orderId(), message.creditCardToken(),
        message.price()));
    PaymentFinished paymentFinished = new PaymentFinished(message.orderId(), response.status(), response.code(),
        "payment-finished-exchange",
        "payment-finished-route-key");
    this.queueBroken.publisher(paymentFinished);
  }

}
