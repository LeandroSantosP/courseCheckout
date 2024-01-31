package com.courseapi.infra.repositories.JpaRepositories;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.domain.entities.Course;
import com.courseapi.infra.execptions.NotFound;

import lombok.NonNull;

@Primary
public interface CourseRepositoryJpa extends JpaRepository<CourseJpa, String>, CourseRepository {

  @Override
  default Course get(@NonNull String courseId) {
    var couseData = this.findById(courseId).orElseThrow(() -> new NotFound("Course Not Found"));
    return new Course(couseData.getId(), couseData.getName(), couseData.getDescription(), couseData.getDuration(),
        couseData.getPrice());
  }

  @Override
  default String save(Course course) {
    var couseData = this
        .save(new CourseJpa(course.getId(), course.getName(), course.getDescription(), course.getDuration(),
            course.getPrice()));
    return couseData.getId();
  }

}
