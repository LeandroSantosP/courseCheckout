package com.courseapi.application.repositories;
import com.courseapi.domain.entities.Order;

public interface OrderRepository {

  String save(Order order);

  Order get(String orderId);

  void delete(String orderId);

  void update(Order order);

}
