package com.courseapi.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.application.usecases.StokeBoundContext.StokeService;
import com.courseapi.application.usecases.StokeBoundContext.StokeService.MakeStokeEntryInput;
import com.courseapi.domain.entities.Course;

@SpringBootTest
public class StokeServiceTest {

  @Autowired
  StokeService stokeService;

  @Autowired
  CourseRepository courseRepository;

  @Test
  void shouldBePossibleCreateAnEntryAndCalcThemOnStoke() {
    var courseId = courseRepository.save(Course.create("Learn Java", "dec", 1000, 99, "test.png", 5.0));
    stokeService.makeStokeEntry(new MakeStokeEntryInput(courseId, "in", 10));
    stokeService.makeStokeEntry(new MakeStokeEntryInput(courseId, "out", 2));
    var output = stokeService.calculateStokeEntry(courseId);
    assertEquals(output.total(), 8);
  }

}
