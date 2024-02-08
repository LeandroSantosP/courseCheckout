package com.courseapi.infra.execptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomError extends RuntimeException {
  private HttpStatus status = HttpStatus.BAD_REQUEST;
  private String type = "default";

  public CustomError(String message) {
    super(message);
  }

  public CustomError(String message, HttpStatus status, String type) {
    super(message);
    this.status = status;
    this.type = type;
  }
}
