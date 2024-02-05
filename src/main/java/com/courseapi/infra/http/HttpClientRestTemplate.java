package com.courseapi.infra.http;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import lombok.NonNull;

@Component
public class HttpClientRestTemplate implements HttpClient {

  private final String baseUrl = "http://localhost:8080";

  @Override
  public <Input, Output> Output fetchByPath(Input path, @NonNull Class<Output> responseType) {
    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<Output> response = restTemplate.getForEntity(baseUrl + "/" + path, responseType);
    return (response.getBody());
  }

}
