package com.courseapi.application.usecases.StokeBoundContext;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseapi.application.repositories.CourseRepository;
import com.courseapi.application.repositories.StokeEntryRepository;
import com.courseapi.domain.entities.Course;
import com.courseapi.domain.entities.StokeEntry;
import com.courseapi.domain.entities.StokeEntryCalculator;
import com.courseapi.domain.entities.StokeEntryFactory;

@Service
public class StokeService {

  @Autowired
  private StokeEntryRepository stokeEntryRepository;

  @Autowired
  private CourseRepository courseRepository;

  public String makeStokeEntry(MakeStokeEntryInput input) {
    Course course = this.courseRepository.get(input.courseId);
    StokeEntry stokeEntry = StokeEntryFactory.create(input.operation(), course.getId(), input.amount());
    return this.stokeEntryRepository.save(stokeEntry);
  }

  public CalculateStokeEntryOutput calculateStokeEntry(String courseId) {
    Course course = this.courseRepository.get(courseId);
    ArrayList<StokeEntry> stokeEntries = this.stokeEntryRepository.getAllByProductId(course.getId());
    var total = StokeEntryCalculator.exec(stokeEntries);
    return new CalculateStokeEntryOutput(courseId, total);
  }

  public record MakeStokeEntryInput(String courseId, String operation, int amount) {
  }

  public record CalculateStokeEntryOutput(String courseId, int total) {
  }
}
