package com.courseapi.domain.entities;

import org.springframework.web.multipart.MultipartFile;

public class FileContent {

  MultipartFile file;

  public FileContent(MultipartFile file) {
    this.file = file;
  }

}
