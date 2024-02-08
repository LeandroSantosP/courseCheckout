package com.courseapi.infra.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.courseapi.application.usecases.CheckoutBoundContext.CreateCourse;
import com.courseapi.application.usecases.CheckoutBoundContext.CreateCourse.CreateCourseInput;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/course")
public class CourseController {

  @Autowired
  CreateCourse createCourse;

  @PostMapping(value = "/")
  public ResponseEntity<CreateCoursecOutputController> createCourse(@Valid CreateCourseInputController input)
      throws IOException {
    String output = createCourse
        .execute(new CreateCourseInput(input.getName(),
            input.getDescription(),
            input.getPrice(),
            input.getDuration(),
            input.getImage(),
            input.getIof_percentage()));
    return ResponseEntity.ok(new CreateCoursecOutputController(output));
  }

  private record CreateCoursecOutputController(String course_id) {
  }

}

@Getter
@AllArgsConstructor
class CreateCourseInputController {

  private MultipartFile image;
  private String name;
  private String description;
  private double price;
  private int duration;
  private double iof_percentage;

}
