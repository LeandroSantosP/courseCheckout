package com.courseapi.infra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.domain.entities.Course;

@RestController
public class CheckOutContorllerTest {

  @Autowired
  private CourseRepository courseRepositoryJpa;

  @GetMapping("/get")
  public ResponseEntity<String> get() {
    var a = this.courseRepositoryJpa.save(new Course("12345", "Java Code", "des", 1000, 99.99, "", 5));
    var s = this.courseRepositoryJpa.get(a);
    return ResponseEntity.ok("amount: " + s.getName());
  }

}
