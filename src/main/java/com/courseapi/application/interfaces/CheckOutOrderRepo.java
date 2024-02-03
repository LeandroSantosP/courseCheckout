package com.courseapi.application.interfaces;

import com.courseapi.domain.entities.Order;

public interface CheckOutOrderRepo {
  public String save(Order order);
}
