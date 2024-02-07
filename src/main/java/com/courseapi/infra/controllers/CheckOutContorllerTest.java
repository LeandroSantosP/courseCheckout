package com.courseapi.infra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courseapi.application.usecases.CheckoutBoundContext.CheckOut;
import com.courseapi.application.usecases.CheckoutBoundContext.GetOrder;
import com.courseapi.application.usecases.CheckoutBoundContext.GetOrder.GetOrderOutput;
import com.courseapi.application.usecases.CheckoutBoundContext.CheckOut.CheckOutInput;
import com.courseapi.application.usecases.CheckoutBoundContext.CheckOut.CheckOutOutput;

@RestController
@RequestMapping("/checkout")
public class CheckOutContorllerTest {

  @Autowired
  private CheckOut checkOut;

  @Autowired
  private GetOrder getOrder;

  private record CheckOutInputController(String courseId, String name, String email, String creditCardToken) {
  }

  @PostMapping("/create")
  public ResponseEntity<CheckOutOutput> getStokeProductAmount(@RequestBody CheckOutInputController input) {
    var output = checkOut
        .execute(new CheckOutInput(input.courseId(), input.name(), input.email(), input.creditCardToken));
    return ResponseEntity.ok(output);
  }

  @GetMapping("/{order_id}")
  public GetOrderOutput getOrder(@PathVariable String order_id) {
    return getOrder.execute(order_id);
  }

}
