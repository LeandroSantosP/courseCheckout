package com.courseapi.application.usecases.CheckoutBoundContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.application.repositories.OrderRepository;
import com.courseapi.domain.entities.Course;
import com.courseapi.domain.entities.Order;
import com.courseapi.domain.messages.OrderCreate;
import com.courseapi.infra.queue.QueueBroken;

@Service
public class CheckOut {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private QueueBroken queueBroken;

  public record CheckOutInput(String courseId, String name, String email, String creditCardToken) {
  }

  public record CheckOutOutput(String orderId, String message) {
  }

  public CheckOutOutput execute(CheckOutInput input) {
    Course course = this.courseRepository.get(input.courseId);
    Order order = Order.create(course.getId(), input.name(), input.email(), course.getPrice());
    this.orderRepository.save(order);

    var message = new OrderCreate(
        order.getId(),
        course.getPrice(),
        input.creditCardToken(),
        "order-create-exchange",
        "order-create-route-key");

    this.queueBroken.publisher(message);
    return new CheckOutOutput(order.getId(), "Order created successfully, payment in process...");
  }
}
