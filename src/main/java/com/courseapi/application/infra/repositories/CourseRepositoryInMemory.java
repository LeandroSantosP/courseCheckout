package com.courseapi.application.infra.repositories;

import java.util.ArrayList;

import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.domain.entities.Course;

public class CourseRepositoryInMemory implements CourseRepository {

  private ArrayList<Course> courses;

  public CourseRepositoryInMemory() {
    this.courses = new ArrayList<Course>();
  }

  @Override
  public String save(Course course) {
    this.courses.add(course);
    return course.id();
  }

  @Override
  public Course get(String courseId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'get'");
  }

}
