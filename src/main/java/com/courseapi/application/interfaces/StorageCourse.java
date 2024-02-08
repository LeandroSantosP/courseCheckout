package com.courseapi.application.interfaces;

import java.awt.image.BufferedImage;

import com.courseapi.domain.entities.MyFile;

public interface StorageCourse {

  String persiste(MyFile content, String indentify);

  BufferedImage get(String ref);

  void clear();
}
