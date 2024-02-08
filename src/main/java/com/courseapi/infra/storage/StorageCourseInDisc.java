package com.courseapi.infra.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Component;
import com.courseapi.application.interfaces.StorageCourse;
import com.courseapi.domain.entities.MyFile;
import com.courseapi.infra.execptions.CustomError;
import com.courseapi.infra.settings.FileStorageProperties;

@Component
public class StorageCourseInDisc implements StorageCourse {

  private Path fileStorageLocation;

  public StorageCourseInDisc(FileStorageProperties fileStorageProps) {
    this.fileStorageLocation = Paths.get(fileStorageProps.getUploadDir())
        .toAbsolutePath().normalize();
  }

  @Override
  public String persiste(MyFile myFile, String indentify) {
    String targetLocation = null;
    try {
      targetLocation = myFile.writeInDisc(indentify, fileStorageLocation);
      return targetLocation;
    } catch (IOException e) {
      e.printStackTrace();
      throw new CustomError(
          "Error on uploading file: " + myFile.getFilename() + ", to follow location: " + targetLocation);
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

  @Override
  public void clear() {
    try {
      Files.walk(fileStorageLocation)
          .filter(Files::isRegularFile)
          .map(Path::toFile)
          .forEach(File::delete);
    } catch (IOException e) {
      System.err.println("Error clearing storage: " + e.getMessage());
      e.printStackTrace();
    }
  }

}
