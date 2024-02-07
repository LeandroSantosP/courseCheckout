package com.courseapi.infra.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import lombok.NonNull;

@Component
public class HttpClientRestTemplate implements HttpClient {

  @Value("${server.port}")
  private String server_port;

  private final String baseUrl = "http://localhost:8085";

  @Override
  public <Input, Output> Output fetchByPath(Input path, @NonNull Class<Output> responseType) {
    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<Output> response = restTemplate.getForEntity(baseUrl + "/" + path, responseType);
    return (response.getBody());
  }

}
