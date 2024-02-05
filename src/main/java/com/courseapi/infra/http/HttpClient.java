package com.courseapi.infra.http;

import jakarta.validation.constraints.NotNull;

public interface HttpClient {

  <Input, Output> Output fetchByPath(Input path, @NotNull Class<Output> responseType);

}
