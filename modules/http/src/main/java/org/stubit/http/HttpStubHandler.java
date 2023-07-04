package org.stubit.http;

import static java.util.UUID.randomUUID;
import static org.stubit.http.StubbedResponse.response;
import static org.stubit.http.Stubbing.stub;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

record HttpStubHandler(String baseUri, List<Stubbing> stubbedResponses) implements HttpHandler {

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    final var request =
        new HttpRequest(
            httpExchange.getRequestMethod(),
            httpExchange.getRequestURI(),
            new String(httpExchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8));
    final var response =
        switch (request.method()) {
          case "POST" -> handlePostRequest(request);
          default -> handleGetRequest(request);
        };
    httpExchange.getResponseHeaders().putAll(response.headers());
    httpExchange.sendResponseHeaders(response.statusCode(), response.body().length());
    try (var responseBodyOutputStream = httpExchange.getResponseBody()) {
      responseBodyOutputStream.write(response.body().getBytes());
    }
    httpExchange.close();
  }

  private StubbedResponse handleGetRequest(HttpRequest request) {
    final var stubbing =
        stubbedResponses.stream().filter(stub -> stub.predicate().test(request)).findFirst();
    return stubbing
        .map(Stubbing::response)
        .orElse(response().body("No stubbing for %s".formatted(request)).statusCode(404));
  }

  private StubbedResponse handlePostRequest(HttpRequest request) {
    final var stubbing =
        stubbedResponses.stream().filter(stub -> stub.predicate().test(request)).findFirst();
    return stubbing
        .map(Stubbing::response)
        .orElseGet(
            () -> {
              final var uri = URI.create("%s%s%s".formatted(baseUri, request.uri(), randomUUID()));
              stubbedResponses.add(
                  stub()
                      .path(uri.getPath())
                      .method("GET", "PATCH", "PUT")
                      .returns(response().body(request.body()).statusCode(200)));
              return response()
                  .body("Added stubbing for %s".formatted(request.uri().getPath()))
                  .header("Location", uri.toString())
                  .statusCode(201);
            });
  }
}
