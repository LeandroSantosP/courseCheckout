package com.courseapi.application.repositories;

import org.springframework.stereotype.Repository;
import com.courseapi.domain.entities.Course;

@Repository
public interface CourseRepository {

  String save(Course course);

  Course get(String courseId);

}
