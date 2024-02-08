package com.courseapi.unity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import com.courseapi.domain.entities.Order;
import com.courseapi.infra.execptions.InvalidField;

public class OrderTest {

  @Test
  void shouldBeAbleCreateAOrder() {
    Order order = Order.create("courseId", "Java for beginners", "john.dow@gmail.com", 1999);
    assertEquals("leandro", "leandro");
    assertEquals(order.getCourseId(), "courseId");
    assertEquals(order.getEmail(), "john.dow@gmail.com");
    assertEquals(order.getName(), "Java for beginners");
    assertEquals(order.getPrice(), 1999);
  }

  @Test
  void shouldNotBeAbleCreateAOrderWithInvalidEmail() {
    assertThrows(InvalidField.class, () -> {
      Order.create("courseId", "Java for beginners", "john.dow@", 1999);
    });
  }

  @Test
  void shouldGetTheOrderPriceAndCalculateIofOf5Percentage() {
    Order order = Order.create("courseId", "Java for beginners", "john.dow@gmail.com", 1999);
    assertEquals(order.getTotal("SP"), 2098.95);
  }

}
