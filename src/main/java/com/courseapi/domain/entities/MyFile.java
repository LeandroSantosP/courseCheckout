package com.courseapi.domain.entities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import com.courseapi.infra.execptions.CustomError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyFile {

  private String filename;
  private String content_type;
  private long size;
  private byte[] bytes;

  public String writeInDisc(String indentify, Path fileStorageLocation) throws IOException {
    String[] fileNameArr = this.filename.split("\\.");
    String newFileName = fileNameArr[0] + "-" + indentify + "." + fileNameArr[1];
    Path targetLocation = fileStorageLocation.resolve(newFileName);
    File targetFile = new File(targetLocation.toString());
    try (FileOutputStream fos = new FileOutputStream(targetFile)) {
      fos.write(this.bytes);
    }
    return targetLocation.toAbsolutePath().toString();
  }

}
