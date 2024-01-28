package com.courseapi.domain.entities;

import org.springframework.stereotype.Component;

@Component
public class Logger {

  public static void log(String message) {
    System.out.println(message);
  }

}
