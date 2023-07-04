package org.stubit.http;

import java.net.URI;

class HttpRequestTestDataBuilder {

  private String method = "GET";
  private String uri = "http://localhost:4711";
  private String body = "Some content";

  static HttpRequestTestDataBuilder httpRequest() {
    return new HttpRequestTestDataBuilder();
  }

  public HttpRequestTestDataBuilder method(String method) {
    this.method = method;
    return this;
  }

  public HttpRequestTestDataBuilder uri(String uri) {
    this.uri = uri;
    return this;
  }

  public HttpRequestTestDataBuilder path(String path) {
    this.uri += path;
    return this;
  }

  public HttpRequestTestDataBuilder body(String body) {
    this.body = body;
    return this;
  }

  public HttpRequest build() {
    return new HttpRequest(method, URI.create(uri), body);
  }
}
