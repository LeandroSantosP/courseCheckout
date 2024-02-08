package com.courseapi.application.usecases.CheckoutBoundContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.courseapi.application.interfaces.StorageCourse;
import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.domain.entities.Course;
import com.courseapi.domain.entities.MyFile;

// To-DO

@Service
public class GetCourse {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private StorageCourse storageCourse;

  public GetCourseOutput execute(String courseId) {
    Course course = this.courseRepository.get(courseId);
    var image = this.storageCourse.get(course.getRef());
    return new GetCourseOutput(course.getId(), course.getName(), course.getDescription(), course.getDuration(),
        course.getPrice(), course.getRef(), course.getIofPersentage(), image);
  }

  public record GetCourseOutput(String id,
      String course_name, String description, int duration,
      double price, String ref, double iofPersentage,
      MyFile image) {
  }

}
