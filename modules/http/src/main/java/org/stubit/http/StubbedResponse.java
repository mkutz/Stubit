package org.stubit.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A {@link StubbedResponse} defines the response that will be returned by the stub.
 *
 * <p>Please use the {@link #response()} initializer to create this with a fluent builder API.
 *
 * @param statusCode status code that will be used in the response
 * @param body body that will be used for the response
 * @param headers headers that will be set in the response
 */
public record StubbedResponse(int statusCode, String body, Map<String, List<String>> headers) {

  /**
   * @return a newly created {@link Builder}
   */
  public static Builder response() {
    return new Builder();
  }

  /** A fluent API builder for {@link StubbedResponse}. */
  public static class Builder {

    private String body;

    private final Map<String, List<String>> headers = new HashMap<>();

    /**
     * Sets the body for the response.
     *
     * @param body that will be used for the response
     * @return this
     */
    public Builder body(String body) {
      this.body = body;
      return this;
    }

    /**
     * Adds a header that will be set in the response.
     *
     * @param name header name
     * @param value header value or values
     * @return this
     */
    public Builder header(String name, String... value) {
      headers.computeIfAbsent(name, key -> new ArrayList<>()).addAll(List.of(value));
      return this;
    }

    /**
     * Sets the status code for the response and builds the {@link StubbedResponse}.
     *
     * @param statusCode status code that will be used in the response
     * @return the built {@link StubbedResponse}
     */
    public StubbedResponse statusCode(int statusCode) {
      return new StubbedResponse(statusCode, body, headers);
    }
  }
}
