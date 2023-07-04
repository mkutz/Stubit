package org.stubit.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record StubbedResponse(int statusCode, String body, Map<String, List<String>> headers) {

  public static Builder response() {
    return new Builder();
  }

  public static class Builder {

    private String body;

    private final Map<String, List<String>> headers = new HashMap<>();

    public Builder body(String body) {
      this.body = body;
      return this;
    }

    public Builder header(String name, String... value) {
      headers.computeIfAbsent(name, key -> new ArrayList<>()).addAll(List.of(value));
      return this;
    }

    public StubbedResponse statusCode(int statusCode) {
      return new StubbedResponse(statusCode, body, headers);
    }
  }
}
