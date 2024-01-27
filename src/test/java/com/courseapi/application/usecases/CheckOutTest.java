package com.courseapi.application.usecases;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.courseapi.application.usecases.CheckOut.CheckOutInput;

@SpringBootTest
public class CheckOutTest {

  @Test
  public void shouldBeAbleToCheckout() {

    CourseRepository CourseRepository = new CourseRepositoryInMemory();
    OrderRepository OrderRepository = new OrderRepositoryInMemory();
    CheckOut checkOut = new CheckOut(CourseRepository, OrderRepository);
    CheckOutInput input = new CheckOutInput("6d0b1e87-e755-4339-a235-a4fdbc8af45a", "John Doe",
        "john.doe@gmail.com", "123456789");
    var output = checkOut.execute(input);
    assertNotNull(output);
  }

}
