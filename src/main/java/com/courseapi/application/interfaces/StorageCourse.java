package com.courseapi.application.interfaces;

import java.awt.image.BufferedImage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageCourse {

  String persiste(MultipartFile content);

  BufferedImage get(String ref);
}
