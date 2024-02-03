package com.courseapi.unity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import com.courseapi.domain.entities.Course;

@SpringBootTest
public class CourseTest {

  @Test
  void shouldBeAbleCreateAnCourse() {
    Course course = Course.create("Learn Java", "Addding some description", 100, 0, "./myimg.png");
    assertEquals(course.getName(), "Learn Java");
    assertEquals(course.getDescription(), "Addding some description");
    assertEquals(course.getPrice(), 0);
  }

}
