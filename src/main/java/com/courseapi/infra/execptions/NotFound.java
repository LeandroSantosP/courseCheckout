package com.courseapi.infra.execptions;

public class NotFound extends CustomError {

  public NotFound(String message) {
    super(message);
  }

  public NotFound() {
    super("Product not fould!");
  }

}
