package org.stubit.http;

import static java.util.Collections.*;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

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
      handler = new HttpStubHandler(address, new ArrayList<>());
      httpServer.createContext("/", handler);
    } catch (IOException e) {
      throw new HttpStubCreationException(e);
    }
  }

  public HttpStub stubResponse(HttpStubbing stubbedResponse) {
    handler.stubbedResponses().add(stubbedResponse);
    return this;
  }

  public HttpStub stubResponses(HttpStubbing... stubbedResponses) {
    addAll(handler.stubbedResponses(), stubbedResponses);
    return this;
  }

  public HttpStub reset() {
    handler.stubbedResponses().clear();
    return this;
  }

  /**
   * @return the {@link HttpStub} base address
   */
  public String address() {
    return address;
  }

  @Override
  public void close() {
    httpServer.stop(0);
  }
}
