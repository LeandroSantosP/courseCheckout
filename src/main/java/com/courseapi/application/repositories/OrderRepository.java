package com.courseapi.application.repositories;

import org.springframework.stereotype.Repository;

import com.courseapi.domain.entities.Order;

@Repository
public interface OrderRepository {

  String save(Order order);

  Order get(String orderId);

  void delete(String orderId);

  void update(Order order);

}
