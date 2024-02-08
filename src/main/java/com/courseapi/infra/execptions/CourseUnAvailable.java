package com.courseapi.infra.execptions;

import lombok.Getter;

@Getter
public class CourseUnAvailable extends CustomError {
  public CourseUnAvailable() {
    super("Course is not available!");
  }
}
