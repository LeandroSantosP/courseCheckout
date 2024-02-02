package com.courseapi.infra.repositories.inMemory;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;

import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.domain.entities.Course;
import com.courseapi.infra.execptions.NotFound;

@Repository
public class CourseRepositoryInMemory implements CourseRepository {

  private ArrayList<Course> courses;

  public CourseRepositoryInMemory() {
    this.courses = new ArrayList<Course>();

    this.courses
        .add(
            new Course("6d0b1e87-e755-4339-a235-a4fdbc8af45a", "Learn Java", "Addding some description", 9999,
                22222, ""));

  }

  public String save(Course course) {
    this.courses.add(course);
    return course.getId();
  }

  public Course get(String courseId) {
    return this.courses.stream().filter(course -> course.getId().equals(courseId)).findFirst()
        .orElseThrow(() -> new NotFound("Course Not Found"));
  }
}
