package com.courseapi.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.application.repositories.OrderRepository;
import com.courseapi.application.repositories.StokeEntryRepository;
import com.courseapi.application.usecases.CheckoutBoundContext.CheckOut;
import com.courseapi.application.usecases.CheckoutBoundContext.GetOrder;
import com.courseapi.application.usecases.CheckoutBoundContext.CheckOut.CheckOutInput;
import com.courseapi.domain.entities.Course;
import com.courseapi.domain.entities.StokeEntryIn;

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

  @Autowired
  StokeEntryRepository stokeServiceRepository;

  @Test
  void testExecute() throws InterruptedException {
    
    var courseId = this.courseRepository.save(
        Course.create("Learn Java", "Addding some description", 999,
            22222, "img-name-1.png", 5.0));
        

    stokeServiceRepository.save(new StokeEntryIn(UUID.randomUUID().toString(), courseId, 10));
    stokeServiceRepository.count(courseId);

    CheckOutInput input = new CheckOutInput(courseId, "John Doe",
        "john.doe@gmail.com", "123456789");
    var output = checkOut.execute(input);
    assertEquals(output.message(), "Order created successfully, payment in process...");
    // wait for payment
    Thread.sleep(200);
    var getOrderOutput = this.getOrder.execute(output.orderId());
    System.out.println("STAUTS " + getOrderOutput.status());

    //assertNotNull(getOrderOutput);
    assertEquals(getOrderOutput.orderId(), output.orderId());
    assertEquals(getOrderOutput.couseId(),
        courseId);
    assertEquals(getOrderOutput.name(), "John Doe");
    assertEquals(getOrderOutput.email(), "john.doe@gmail.com");
    assertEquals(getOrderOutput.price(), 222.22);
    assertEquals(getOrderOutput.status(), "payed");

    var stoke_entries = stokeServiceRepository.getAllByProductId(courseId);
    int totalAfterPurchers = stoke_entries.stream()
        .mapToInt(i -> "in".equals(i.getOperation()) ? i.getAmount() : -i.getAmount()).reduce(0,
            Integer::sum);
    assertEquals(totalAfterPurchers, 9);
  }

}
