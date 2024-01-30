package com.courseapi.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.courseapi.application.usecases.CheckoutBoundContext.CheckOut;
import com.courseapi.application.usecases.CheckoutBoundContext.GetOrder;
import com.courseapi.application.usecases.CheckoutBoundContext.CheckOut.CheckOutInput;

@SpringBootTest
public class CheckOutTest {

  @Autowired
  CheckOut checkOut;

  @Autowired
  GetOrder getOrder;

  @Test
  void testExecute() throws InterruptedException {
    CheckOutInput input = new CheckOutInput("6d0b1e87-e755-4339-a235-a4fdbc8af45a", "John Doe",
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
        "6d0b1e87-e755-4339-a235-a4fdbc8af45a");
    assertEquals(getOrderOutput.name(), "John Doe");
    assertEquals(getOrderOutput.email(), "john.doe@gmail.com");
    assertEquals(getOrderOutput.price(), 222.22);
    assertEquals(getOrderOutput.status(), "payed");
  }

}
