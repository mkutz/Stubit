package org.stubit.http;

import java.io.IOException;

/**
 * Exception for startup failures.
 */
public class HttpStubCreationException extends RuntimeException {

  /**
   * @param cause the causing {@link IOException}
   */
  public HttpStubCreationException(IOException cause) {
    super(cause);
  }
}
