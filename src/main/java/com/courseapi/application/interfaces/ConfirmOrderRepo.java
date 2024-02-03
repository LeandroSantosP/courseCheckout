package com.courseapi.application.interfaces;

import com.courseapi.domain.entities.Order;

public interface ConfirmOrderRepo {
  Order get(String orderId);

  void update(Order order);
}