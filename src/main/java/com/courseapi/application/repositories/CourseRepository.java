package com.courseapi.application.repositories;

import com.courseapi.domain.entities.Course;

import lombok.NonNull;

public interface CourseRepository {

  String save(Course course);

  Course get(@NonNull String courseId);

}
