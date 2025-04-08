package org.stubit.http;

import static java.util.Collections.addAll;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.function.Predicate;
import org.jspecify.annotations.NullMarked;

/**
 * A {@link HttpStub} wraps an {@link HttpServer} that is started locally on a dynamic port to serve
 * fixed stubbed responses ({@link Stubbing}s).
 *
 * <p>This can be used to simulate (stub) a remote service used in your production code. In order to
 * do so, you need to configure your code to call the {@link HttpStub}'s {@link #address() address}
 * instead of the actual service address.
 */
@NullMarked
public class HttpStub implements AutoCloseable {

  private final HttpStubHandler handler;
  private final HttpServer httpServer;
  private final String address;

  /** Creates and starts the HttpStub. */
  public HttpStub() {
    try {
      httpServer = HttpServer.create(new InetSocketAddress(0), 0);
      httpServer.start();
      address = "http://localhost:%d".formatted(httpServer.getAddress().getPort());
      handler = new HttpStubHandler(address);
      httpServer.createContext("/", handler);
    } catch (IOException e) {
      throw new HttpStubCreationException(e);
    }
  }

  /**
   * @param stubbings {@link Stubbing}(s) to be added
   * @return this
   */
  public HttpStub add(Stubbing... stubbings) {
    addAll(handler.stubbedResponses(), stubbings);
    return this;
  }

  /**
   * Removes all {@link Stubbing}s.
   *
   * @return this
   */
  public HttpStub reset() {
    handler.stubbedResponses().clear();
    handler.receivedRequests().clear();
    return this;
  }

  /**
   * @return the {@link HttpStub} base address
   */
  public String address() {
    return address;
  }

  /**
   * Returns all received request that match the given predicate.
   *
   * @param predicate a {@link HttpRequest} {@link Predicate}
   * @return the first received request that matches the given predicate
   */
  public List<HttpRequest> receivedRequests(Predicate<HttpRequest> predicate) {
    return handler.receivedRequests().stream().filter(predicate).toList();
  }

  /**
   * Returns all received requests.
   *
   * @return all received requests
   */
  public List<HttpRequest> receivedRequests() {
    return handler.receivedRequests();
  }

  @Override
  public void close() {
    httpServer.stop(0);
  }
}
