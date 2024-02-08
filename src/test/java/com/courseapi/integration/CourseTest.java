package com.courseapi.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import com.courseapi.application.interfaces.StorageCourse;
import com.courseapi.application.usecases.CheckoutBoundContext.CreateCourse;
import com.courseapi.application.usecases.CheckoutBoundContext.CreateCourse.CreateCourseInput;
import com.courseapi.application.usecases.CheckoutBoundContext.GetCourse;

@SpringBootTest
public class CourseTest {

  @Autowired
  CreateCourse createCourse;

  @Autowired
  GetCourse getCourse;

  @Autowired
  StorageCourse storageCourse;

  @Test
  void shouldBeAbleToCreateCourseAndGetIt() throws FileNotFoundException, IOException {
    File file = new File("src/main/resources/img/myImg.png");
    MockMultipartFile image = new MockMultipartFile("file", file.getName(),
        "image/png", new FileInputStream(file));
    CreateCourseInput input = new CreateCourseInput("Learn Java", "desc", 9999,
        100, image, 5.0);
    var id = this.createCourse.execute(input);

    var output = this.getCourse.execute(id);
    assertEquals(output.course_name(), "Learn Java");
    assertEquals(output.description(), "desc");
    assertEquals(output.price(), 99.99);
    assertEquals(output.image().getSize(), image.getSize());

    assertEquals(image.getBytes()[10], output.image().getBytes()[10]);
    this.storageCourse.clear();
  }

}
