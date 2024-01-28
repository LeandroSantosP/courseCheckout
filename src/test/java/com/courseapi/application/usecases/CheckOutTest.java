package com.courseapi.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.courseapi.application.usecases.CheckOut.CheckOutInput;

@SpringBootTest
public class CheckOutTest {

  @Autowired
  CheckOut checkOut;

  @Autowired
  GetOrder getOrder;

  @Test
  void testExecute() {
    CheckOutInput input = new CheckOutInput("6d0b1e87-e755-4339-a235-a4fdbc8af45a", "John Doe",
        "john.doe@gmail.com", "123456789");
    var orderId = checkOut.execute(input);
    assertNotNull(orderId);

    var getOrderOutput = this.getOrder.execute(orderId);

    assertNotNull(getOrderOutput);
    assertEquals(getOrderOutput.orderId(), orderId);
    assertEquals(getOrderOutput.couseId(), "6d0b1e87-e755-4339-a235-a4fdbc8af45a");
    assertEquals(getOrderOutput.name(), "John Doe");
    assertEquals(getOrderOutput.email(), "john.doe@gmail.com");
    assertEquals(getOrderOutput.price(), 222.22);
    assertEquals(getOrderOutput.status(), "payed");

  }

}
