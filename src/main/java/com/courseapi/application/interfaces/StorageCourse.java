package com.courseapi.application.interfaces;

import com.courseapi.domain.entities.MyFile;

public interface StorageCourse {

  String persiste(MyFile content, String indentify);

  MyFile get(String ref);

  void clear();
}
