package com.courseapi.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import com.courseapi.application.interfaces.StorageCourse;
import com.courseapi.application.usecases.CheckoutBoundContext.CreateCourse;
import com.courseapi.application.usecases.CheckoutBoundContext.CreateCourse.CreateCourseInput;

@SpringBootTest
public class CreateCourseTest {

  @Autowired
  CreateCourse createCourse;

  @Autowired
  StorageCourse storageCourse;

  @Test
  void shouldBeAbleToCreateCourse() throws FileNotFoundException, IOException {
    File file = new File("src/main/resources/img/myImg.png");
    MockMultipartFile image = new MockMultipartFile("file", file.getName(),
        "image/png", new FileInputStream(file));
    CreateCourseInput input = new CreateCourseInput("Learn Java", "desc", 999,
        100, image, 5.0);
    var id = this.createCourse.execute(input);
    assertNotNull(id);
  }

}
