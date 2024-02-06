package com.courseapi.application.usecases.CheckoutBoundContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.courseapi.application.interfaces.CheckOutOrderRepo;
import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.domain.entities.Course;
import com.courseapi.domain.entities.Order;
import com.courseapi.domain.messages.OrderCreate;
import com.courseapi.infra.http.HttpClient;
import com.courseapi.infra.queue.QueueBroken;;;;;

@Service
public class CheckOut {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private CheckOutOrderRepo orderRepository;

  @Autowired
  private QueueBroken queueBroken;

  @Autowired
  private HttpClient httpClient;

  public record CheckOutInput(String courseId, String name, String email, String creditCardToken) {
  }

  public record CheckOutOutput(String orderId, String message) {
  }

  public CheckOutOutput execute(CheckOutInput input) {
    Course course = this.courseRepository.get(input.courseId);
    Integer courseStokeAmount = this.httpClient.fetchByPath("/stoke-entry/" + course.getId(), Integer.class);

    if (courseStokeAmount <= 0) {
      throw new RuntimeException("Course is not available!");
    }

    Order order = Order.create(course.getId(), input.name(), input.email(), course.getPrice());
    var orderId = this.orderRepository.save(order);
    var message = new OrderCreate(
        orderId,
        course.getPrice(),
        input.creditCardToken(),
        "order-create-exchange",
        "order-create-route-key",
        course.getId());

    this.queueBroken.publisher(message);
    return new CheckOutOutput(orderId, "Order created successfully, payment in process...");
  }
}
