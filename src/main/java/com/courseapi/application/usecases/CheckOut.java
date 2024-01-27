package com.courseapi.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;

import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.application.repositories.OrderRepository;

public class CheckOut {

  public record CheckOutInput(String courseId, String name, String email, String creditCardToken) {
  }

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CourseRepository courseRepository;

  public CheckOut() {
  }

  public String execute(CheckOutInput input) {
    return "";
  }

}
