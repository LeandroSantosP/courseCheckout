package com.courseapi.infra.repositories.inMemory;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Repository;

import com.courseapi.application.repositories.OrderRepository;
import com.courseapi.domain.entities.Order;
import com.courseapi.infra.execptions.NotFound;

@Repository
public class OrderRepositoryInMemory implements OrderRepository {

  private ArrayList<Order> orders;

  public OrderRepositoryInMemory() {
    this.orders = new ArrayList<Order>();
  }

  @Override
  public String save(Order order) {
    return CompletableFuture.supplyAsync(() -> {
      this.orders.add(order);
      return order.getId();
    }).join();
  }

  @Override
  public Order get(String orderId) {
    return CompletableFuture.supplyAsync(() -> {
      Order order = this.orders.stream().filter(orderItem -> orderItem.getId().equals(orderId)).findFirst()
          .orElseThrow(() -> new NotFound("Order Not Found"));
      return order;
    }).join();
  }

  @Override
  public void delete(String orderId) {
    for (int i = 0; i < this.orders.size(); i++) {
      if (this.orders.get(i).getId().equals(orderId)) {
        this.orders.remove(i);
        break;
      }
    }
  }

  @Override
  public void update(Order order) {
    for (int i = 0; i < this.orders.size(); i++) {
      if (this.orders.get(i).getId().equals(order.getId())) {
        this.orders.set(i, order);
        break;
      }
    }
  }

}
