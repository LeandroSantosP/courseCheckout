package com.courseapi.infra.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.courseapi.infra.settings.FileStorageProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/api/files")
public class FileStorageController {
  private Path fileStorageLocation;

  public FileStorageController(FileStorageProperties fileStorageProps) {
    this.fileStorageLocation = Paths.get(fileStorageProps.getUploadDir())
        .toAbsolutePath().normalize();
  }

  @PostMapping("/upload")
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    try {
      Path targetLocation = fileStorageLocation.resolve(fileName);
      file.transferTo(targetLocation);

      String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
          .path("/api/fileStorage/download/").path(fileName).toUriString();

      return ResponseEntity.ok("Uploaded the file successfully: " + fileDownloadUrl);
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/download/${fileName:.+}")
  public ResponseEntity<Resource> getMethodName(@PathVariable String fileName, HttpServletRequest request)
      throws IOException {
    Path filePath = fileStorageLocation.resolve(fileName).normalize();

    try {
      Resource resource = new UrlResource(filePath.toUri());
      String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
      if (contentType == null) {
        contentType = "application/octet-stream";
      }
      return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
          .body(resource);
    } catch (MalformedURLException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/list")
  public ResponseEntity<List<String>> listFiles() throws IOException {
    List<String> fileNames = Files.list(fileStorageLocation)
        .map(Path::getFileName)
        .map(Path::toString)
        .collect(Collectors.toList());
    return ResponseEntity.ok(fileNames);
  }

}
