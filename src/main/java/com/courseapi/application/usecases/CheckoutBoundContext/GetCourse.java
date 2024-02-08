package com.courseapi.application.usecases.CheckoutBoundContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.courseapi.application.interfaces.StorageCourse;
import com.courseapi.application.repositories.CourseRepository;

// To-DO

@Service
public class GetCourse {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private StorageCourse storageCourse;

  public void execute(Object input) {
  }

}
