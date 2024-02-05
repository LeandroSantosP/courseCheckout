package com.courseapi.application.usecases.CheckoutBoundContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.courseapi.application.interfaces.StorageCourse;
import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.domain.entities.Course;

@Service
public class CreateCourse {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private StorageCourse storageCourse;

  public String execute(CreateCourseInput input) {
    String ref = this.storageCourse.persiste(input.file());
    Course course = Course.create(input.name(), input.description(), input.duration(), input.price(), ref,
        input.iof_persentage());
    return this.courseRepository.save(course);
  }

  public record CreateCourseInput(
      String name, String description, double price, int duration, MultipartFile file, double iof_persentage) {

  }

}
