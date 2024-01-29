package com.courseapi.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.courseapi.application.gateway.PaymentGateway;
import com.courseapi.application.gateway.PaymentGateway.Input;
import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.application.repositories.OrderRepository;
import com.courseapi.domain.entities.Course;
import com.courseapi.domain.entities.Order;

@Service
public class CheckOut {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private PaymentGateway paymentGateway;

  public record CheckOutInput(String courseId, String name, String email, String creditCardToken) {
  }

  public String execute(CheckOutInput input) {
    Course course = this.courseRepository.get(input.courseId);
    System.out.println(course.toString());
    Order order = Order.create(course.getId(), input.name(), input.email(), course.getPrice());
    this.orderRepository.save(order);
    var response = this.paymentGateway
        .processePayment(new Input(order.getId(), input.creditCardToken(), order.getPrice()));
    order.paymentProcess(response.status(), response.code());
    this.orderRepository.update(order);
    return order.getId();
  }
}
