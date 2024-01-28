package com.courseapi.infra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.courseapi.application.usecases.CheckOut;

@RestController
public class CheckOutContorller {

  @Autowired
  CheckOut checkOut;

  @GetMapping("/get")
  public ResponseEntity<String> get() {
    return ResponseEntity.ok("Hello");
  }

}
