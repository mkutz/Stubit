package org.stubit.http;

import static java.net.http.HttpClient.newHttpClient;
import static java.net.http.HttpRequest.newBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.stubit.http.HttpStubbing.stub;
import static org.stubit.http.StubbedResponse.response;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

class HttpStubTest {

  @Test
  void get() throws Exception {
    try (var httpStub = new HttpStub()) {
      httpStub.stubResponse(
          stub().path("/things/some-key").response(response().body("some thing").statusCode(200)));

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
  void get_unknown_resource() throws Exception {
    try (var httpStub = new HttpStub()) {
      var response =
          newHttpClient()
              .send(
                  newBuilder(URI.create(httpStub.address() + "/things/some-key")).GET().build(),
                  HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

      assertThat(response.body()).matches(Pattern.compile("No stubbing for GET /things/some-key"));
      assertThat(response.statusCode()).isEqualTo(404);
    }
  }

  @Test
  void post() throws Exception {
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

      var getResponse =
          httpClient.send(
              newBuilder(URI.create(location)).GET().build(),
              HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

      assertThat(getResponse.body()).isEqualTo("some thing new");
      assertThat(getResponse.statusCode()).isEqualTo(200);
    }
  }
}
