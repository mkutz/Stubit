package org.stubit.http;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class HttpStubTest {

  @Test
  void get_resource() throws Exception {
    try (var httpStub = new HttpStub().add("/things/some-key", "some thing")) {
      var httpClient = HttpClient.newHttpClient();

      var getResponse =
          httpClient.send(
              HttpRequest.newBuilder(URI.create(httpStub.address() + "/things/some-key"))
                  .GET()
                  .build(),
              HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

      assertThat(getResponse.body()).isEqualTo("some thing");
      assertThat(getResponse.statusCode()).isEqualTo(200);
    }
  }

  @Test
  void get_unknown_resource() throws Exception {
    try (var httpStub = new HttpStub()) {
      var httpClient = HttpClient.newHttpClient();

      var getResponse =
          httpClient.send(
              HttpRequest.newBuilder(URI.create(httpStub.address() + "/things/some-key"))
                  .GET()
                  .build(),
              HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

      assertThat(getResponse.body()).isEqualTo("No resource for path /things/some-key");
      assertThat(getResponse.statusCode()).isEqualTo(404);
    }
  }

  @Test
  @Disabled("Posting a resource is not yet implemented")
  void post() throws Exception {
    try (var httpStub = new HttpStub()) {
      var httpClient = HttpClient.newHttpClient();

      var postResponse =
          httpClient.send(
              HttpRequest.newBuilder(URI.create(httpStub.address() + "/things/"))
                  .POST(HttpRequest.BodyPublishers.ofString("some thing new"))
                  .build(),
              HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

      assertThat(postResponse.statusCode()).isEqualTo(201);

      var location = postResponse.headers().firstValue("Location").orElseThrow();

      var getResponse =
          httpClient.send(
              HttpRequest.newBuilder(URI.create(location)).GET().build(),
              HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

      assertThat(getResponse.body()).isEqualTo("some thing new");
      assertThat(getResponse.statusCode()).isEqualTo(200);
    }
  }
}
