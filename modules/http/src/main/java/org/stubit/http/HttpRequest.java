package org.stubit.http;

import java.net.URI;

record HttpRequest(String method, URI uri, String body) {

  @Override
  public String toString() {
    return "%s %s".formatted(method, uri);
  }
}
