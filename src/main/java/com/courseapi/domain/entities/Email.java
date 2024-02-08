package com.courseapi.domain.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.courseapi.infra.execptions.InvalidField;

import lombok.Getter;

public class Email {
  private static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

  @Getter
  private String value;

  public Email(String email) {
    Matcher matcher = Pattern.compile(REGEX_EMAIL).matcher(email);
    if (!matcher.matches()) {
      throw new InvalidField("Invalid Email");
    }
    this.value = email;
  }

}
