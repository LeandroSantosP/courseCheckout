package com.courseapi.application.repositories;

import com.courseapi.domain.entities.Course;

public interface CourseRepository {

  String save(Course course);

  Course get(String courseId);

}
