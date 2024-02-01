package com.courseapi.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.application.repositories.OrderRepository;
import com.courseapi.application.usecases.CheckoutBoundContext.CheckOut;
import com.courseapi.application.usecases.CheckoutBoundContext.GetOrder;
import com.courseapi.application.usecases.CheckoutBoundContext.CheckOut.CheckOutInput;
import com.courseapi.domain.entities.Course;

@SpringBootTest
public class CheckOutTest {

  @Autowired
  CheckOut checkOut;

  @Autowired
  GetOrder getOrder;

  @Autowired
  CourseRepository courseRepository;

  @Autowired
  OrderRepository orderRepository;

  @Test
  void testExecute() throws InterruptedException {
    var courseId = this.courseRepository.save(
        new Course("6d0b1e87-e755-4339-a235-a4fdbc8af45a", "Learn Java", "Addding some description", 9999,
            22222));

    CheckOutInput input = new CheckOutInput(courseId, "John Doe",
        "john.doe@gmail.com", "123456789");
    var output = checkOut.execute(input);
    assertEquals(output.message(), "Order created successfully, payment in process...");
    // wait for payment
    Thread.sleep(200);
    var getOrderOutput = this.getOrder.execute(output.orderId());
    System.out.println("STAUTS " + getOrderOutput.status());

    assertNotNull(getOrderOutput);
    assertEquals(getOrderOutput.orderId(), output.orderId());
    assertEquals(getOrderOutput.couseId(),
        courseId);
    assertEquals(getOrderOutput.name(), "John Doe");
    assertEquals(getOrderOutput.email(), "john.doe@gmail.com");
    assertEquals(getOrderOutput.price(), 222.22);
    assertEquals(getOrderOutput.status(), "payed");
  }

}
