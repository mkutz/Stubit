package org.stubit.http;

import static java.net.http.HttpClient.newHttpClient;
import static java.net.http.HttpRequest.newBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.stubit.http.StubbedResponse.response;
import static org.stubit.http.Stubbing.stub;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class HttpStubTest {

  @Test
  void add_stub() throws Exception {
    try (var httpStub = new HttpStub()) {
      httpStub.add(
          stub().path("/things/some-key").returns(response().body("some thing").statusCode(200)));

      var response =
          newHttpClient()
              .send(
                  newBuilder(URI.create("%s/things/some-key".formatted(httpStub.address())))
                      .GET()
                      .build(),
                  HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

      assertThat(response.body()).isEqualTo("some thing");
      assertThat(response.statusCode()).isEqualTo(200);
    }
  }

  @Test
  void unstubbed_get() throws Exception {
    try (var httpStub = new HttpStub()) {
      var response =
          newHttpClient()
              .send(
                  newBuilder(URI.create(httpStub.address() + "/things/some-key")).GET().build(),
                  HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

      assertThat(response.body()).isEqualTo("No stubbing for GET /things/some-key");
      assertThat(response.statusCode()).isEqualTo(404);
    }
  }

  @Test
  void add_post_stub() throws Exception {
    try (var httpStub = new HttpStub()) {
      var httpClient = newHttpClient();

      httpStub.add(
          stub()
              .method("POST")
              .path("/things/")
              .returns(response().body("success").statusCode(201)));

      var postResponse =
          httpClient.send(
              newBuilder(URI.create(httpStub.address() + "/things/"))
                  .POST(HttpRequest.BodyPublishers.ofString("some thing new"))
                  .build(),
              HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

      assertThat(postResponse.statusCode()).isEqualTo(201);
      assertThat(postResponse.body()).isEqualTo("success");
    }
  }

  @Test
  void unstubbed_post() throws Exception {
    try (var httpStub = new HttpStub()) {
      var httpClient = newHttpClient();

      var postResponse =
          httpClient.send(
              newBuilder(URI.create(httpStub.address() + "/things/"))
                  .POST(HttpRequest.BodyPublishers.ofString("some thing new"))
                  .build(),
              HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

      assertThat(postResponse.statusCode()).isEqualTo(201);

      var location = postResponse.headers().firstValue("Location").orElseThrow();
      assertThat(location).matches("http://localhost:\\d+/things/[0-9a-fA-F-]{36}");

      var getResponse =
          httpClient.send(
              newBuilder(URI.create(location)).GET().build(),
              HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

      assertThat(getResponse.body()).isEqualTo("some thing new");
      assertThat(getResponse.statusCode()).isEqualTo(200);
    }
  }

  @Test
  void reset() throws IOException, InterruptedException {
    try (var httpStub = new HttpStub()) {
      httpStub
          .add(
              stub()
                  .path("/things/some-key")
                  .returns(response().body("some thing").statusCode(200)))
          .reset();

      var response =
          newHttpClient()
              .send(
                  newBuilder(URI.create("%s/things/some-key".formatted(httpStub.address())))
                      .GET()
                      .build(),
                  HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

      assertThat(response.body()).isEqualTo("No stubbing for GET /things/some-key");
      assertThat(response.statusCode()).isEqualTo(404);
    }
  }
}
