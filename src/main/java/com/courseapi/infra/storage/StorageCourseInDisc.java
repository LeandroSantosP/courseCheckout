package com.courseapi.infra.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.courseapi.application.interfaces.StorageCourse;
import com.courseapi.infra.settings.FileStorageProperties;

@Component
public class StorageCourseInDisc implements StorageCourse {

  private Path fileStorageLocation;

  public StorageCourseInDisc(FileStorageProperties fileStorageProps) {
    this.fileStorageLocation = Paths.get(fileStorageProps.getUploadDir())
        .toAbsolutePath().normalize();
  }

  @Override
  public String persiste(MultipartFile content) {
    String fileName = StringUtils.cleanPath(content.getOriginalFilename());

    try {
      Path targetLocation = fileStorageLocation.resolve(fileName);
      content.transferTo(targetLocation);
      return targetLocation.toAbsolutePath().toString();
    } catch (IOException e) {
      System.out.println(e.getMessage());
      throw new RuntimeException("Error on uploading file: " + fileName);
    }
  }

  @Override
  public BufferedImage get(String path) {
    try {
      return ImageIO.read(new File(path));
    } catch (IOException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      throw new RuntimeException("Error getting file: " + e.getMessage());
    }
  }

}
