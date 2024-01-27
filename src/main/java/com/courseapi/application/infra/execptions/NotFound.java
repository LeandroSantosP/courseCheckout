package com.courseapi.application.infra.execptions;

import lombok.Getter;

@Getter
public class NotFound extends RuntimeException {

  private int status = 404;

  public NotFound(String message) {
    super(message);
  }

}
