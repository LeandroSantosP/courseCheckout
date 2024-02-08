package com.courseapi.application.usecases.CheckoutBoundContext;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.courseapi.application.interfaces.StorageCourse;
import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.domain.entities.Course;
import com.courseapi.domain.entities.MyFile;

@Service
public class CreateCourse {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private StorageCourse storageCourse;

  public String execute(CreateCourseInput input) throws IOException {
    Course course = Course.create(input.name(), input.description(), input.duration(), input.price(),
        input.iof_persentage());
    var file = input.file();
    MyFile myFile = new MyFile(file.getOriginalFilename(), file.getContentType(), file.getSize(),
        file.getBytes());
    String ref = this.storageCourse.persiste(myFile, course.getId());
    course.setRef(ref);
    return this.courseRepository.save(course);
  }

  public record CreateCourseInput(
      String name, String description, double price, int duration, MultipartFile file, double iof_persentage) {
  }

}
