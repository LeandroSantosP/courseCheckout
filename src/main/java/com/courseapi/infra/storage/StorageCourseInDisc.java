package com.courseapi.infra.storage;

import java.io.ByteArrayOutputStream;
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
  public MyFile get(String path) {
    try {
      var image = ImageIO.read(new File(path));

      // Get file information
      String filename = new File(path).getName();
      String format = path.substring(path.lastIndexOf('.') + 1);
      String contentType = "image/" + format;
      long size = new File(path).length();

      // Convert image to byte array
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(image, format, baos);
      byte[] bytes = baos.toByteArray();

      return new MyFile(filename, contentType, size, bytes);
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
