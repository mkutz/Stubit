package org.stubit.http;

import java.net.URI;
import org.jspecify.annotations.NullMarked;

/**
 * Represents an HTTP request.
 *
 * @param method the HTTP method
 * @param uri the URI
 * @param body the request body
 */
@NullMarked
record HttpRequest(String method, URI uri, String body) {

  @Override
  public String toString() {
    return "%s %s".formatted(method, uri);
  }
}
