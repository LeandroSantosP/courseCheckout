package com.courseapi.infra.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.courseapi.application.usecases.CheckoutBoundContext.CreateCourse;
import com.courseapi.application.usecases.CheckoutBoundContext.CreateCourse.CreateCourseInput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/course")
public class CourseController {

  @Autowired
  CreateCourse createCourse;

  public record CreateCourseController(
      String name, String description, double price, int duration, double iof_persentage) {
  }

  @PostMapping("/")
  public ResponseEntity<String> createCourse(@RequestBody CreateCourseController input,
      @RequestParam("image") MultipartFile image) {
    String output = createCourse
        .execute(new CreateCourseInput(input.name(), input.description(),
            input.price(), input.duration(), image,
            input.iof_persentage()));
    return ResponseEntity.ok(output);
  }

}
