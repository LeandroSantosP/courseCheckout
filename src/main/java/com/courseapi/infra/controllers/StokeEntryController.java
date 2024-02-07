package com.courseapi.infra.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.courseapi.application.usecases.StokeBoundContext.StokeService;
import com.courseapi.application.usecases.StokeBoundContext.StokeService.CalculateStokeEntryOutput;
import com.courseapi.application.usecases.StokeBoundContext.StokeService.MakeStokeEntryInput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/stoke-entry")
public class StokeEntryController {

  @Autowired
  StokeService stokeService;

  @GetMapping("/{course_id}")
  public ResponseEntity<Integer> getMethodName(@PathVariable String course_id) {
    CalculateStokeEntryOutput output = stokeService.calculateStokeEntry(course_id);
    return ResponseEntity.ok(output.total());
  }

  @PostMapping("/calculate/{course_id}")
  public ResponseEntity<CalculateStokeEntryOutput> calculateStokeEntry(@PathVariable String course_id) {
    CalculateStokeEntryOutput output = stokeService.calculateStokeEntry(course_id);
    return ResponseEntity.ok(output);
  }

  @PostMapping("/create")
  public ResponseEntity<String> createStokeEntry(@RequestBody MakeStokeEntryInput input) {
    var output = stokeService.makeStokeEntry(input);
    return ResponseEntity.ok(output);
  }

}
