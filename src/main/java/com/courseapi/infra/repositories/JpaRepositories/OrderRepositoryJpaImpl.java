package com.courseapi.infra.repositories.JpaRepositories;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;
import com.courseapi.application.repositories.OrderRepository;
import com.courseapi.domain.entities.Order;
import com.courseapi.infra.execptions.NotFound;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Primary
@Repository
class OrderRepositoryJpaImpl implements OrderRepository {

  @PersistenceContext
  private EntityManager entityManager;

  public OrderRepositoryJpaImpl(JpaContext jpaContext) {
    this.entityManager = jpaContext.getEntityManagerByManagedType(OrderJpa.class);
  }

  @Override
  @Transactional
  public String save(Order order) {
    CourseJpa courseJpa = entityManager.find(CourseJpa.class, order.getCourseId());
    OrderJpa orderData = new OrderJpa(order.getId(), order.getStatus(),
        order.getPrice(), order.getEmail(), order.getName(),
        courseJpa);

    OrderJpa mergedOrderData = entityManager.merge(orderData);
    entityManager.flush();
    return mergedOrderData.getId();
  }

  @Override
  @Transactional
  public Order get(String orderId) {
    OrderJpa orderData = this.entityManager.find(OrderJpa.class, orderId);

    if (orderData == null) {
      throw new NotFound("Order Not Found");
    }

    return Order.rebuild(
        orderData.getId(),
        orderData.getStatus(),
        orderData.getCourseJpa().getId(),
        orderData.getName(),
        orderData.getEmail(),
        orderData.getPrice());
  }

  @Override
  public void delete(String orderId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  @Transactional
  public void update(Order order) {
    OrderJpa existingOrder = entityManager.find(OrderJpa.class, order.getId());
    if (existingOrder == null) {
      throw new NotFound("Order Not found for update");
    }
    existingOrder.setStatus(order.getStatus());
    existingOrder.setPrice(order.getPrice());
    existingOrder.setEmail(order.getEmail());
    existingOrder.setName(order.getName());
    entityManager.merge(existingOrder);
    entityManager.flush();
  }

}
