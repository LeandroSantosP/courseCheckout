package com.courseapi.application.repositories;

import com.courseapi.application.interfaces.CheckOutOrderRepo;
import com.courseapi.application.interfaces.ConfirmOrderRepo;

public interface OrderRepository extends CheckOutOrderRepo, ConfirmOrderRepo {
  void delete(String orderId);
}
