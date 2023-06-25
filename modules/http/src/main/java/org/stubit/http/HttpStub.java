package org.stubit.http;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class HttpStub implements AutoCloseable {

  private final HttpServer httpServer;
  private final Map<String, String> resourceMap = new HashMap<>();

  /** Creates and starts the HttpStub with an empty {@link #resourceMap}. */
  public HttpStub() {
    try {
      httpServer = HttpServer.create(new InetSocketAddress(0), 0);
      httpServer.start();
      httpServer.createContext(
          "/",
          httpExchange -> {
            final var path = httpExchange.getRequestURI().getPath();
            String responseBody;
            if (!resourceMap.containsKey(path)) {
              responseBody = String.format("No resource for path %s", path);
              httpExchange.sendResponseHeaders(404, 0);
            } else {
              responseBody = resourceMap.get(path);
              httpExchange.sendResponseHeaders(200, responseBody.length());
            }
            try (var responseBodyOutputStream = httpExchange.getResponseBody()) {
              responseBodyOutputStream.write(responseBody.getBytes());
            }
            httpExchange.close();
          });
    } catch (IOException e) {
      throw new HttpStubCreationException(e);
    }
  }

  /**
   * @return the {@link HttpStub} base address
   */
  public String address() {
    return String.format("http://localhost:%d", httpServer.getAddress().getPort());
  }

  /**
   * Adds a new path and body to be returned.
   *
   * @param path the path the given body should be returned at
   * @param body the body to be returned
   * @return this
   */
  public HttpStub add(String path, String body) {
    resourceMap.put(path, body);
    return this;
  }

  /**
   * Adds the given map to the {@link #resourceMap}
   *
   * @param map new entries for the {@link #resourceMap}
   * @return this
   */
  public HttpStub add(Map<String, String> map) {
    resourceMap.putAll(map);
    return this;
  }

  @Override
  public void close() {
    httpServer.stop(0);
  }
}
