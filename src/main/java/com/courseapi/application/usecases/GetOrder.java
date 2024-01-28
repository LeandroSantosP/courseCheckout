package com.courseapi.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseapi.application.repositories.OrderRepository;
import com.courseapi.domain.entities.Order;

@Service
public class GetOrder {

  @Autowired
  private OrderRepository orderRepository;

  public GetOrderOutput execute(String orderId) {
    Order order = this.orderRepository.get(orderId);
    return new GetOrderOutput(
        order.getId(),
        order.getCourseId(),
        order.getName(),
        order.getEmail(),
        order.getPrice(),
        order.getStatus());
  }

  public record GetOrderOutput(String orderId,
      String couseId,
      String name,
      String email,
      double price,
      String status) {
  }

}
