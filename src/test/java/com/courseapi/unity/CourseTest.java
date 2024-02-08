package com.courseapi.unity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.courseapi.domain.entities.Course;

public class CourseTest {

  @Test
  void shouldBeAbleCreateAnCourse() {
    Course course = Course.create("Learn Java", "Addding some description", 100, 0, 5.0);
    course.setRef("./myimg.png");
    assertEquals(course.getName(), "Learn Java");
    assertEquals(course.getDescription(), "Addding some description");
    assertEquals(course.getPrice(), 0);
  }

}
