package org.stubit.http;

import java.io.IOException;
import org.jspecify.annotations.NullMarked;

/** Exception for startup failures. */
@NullMarked
public class HttpStubCreationException extends RuntimeException {

  /**
   * @param cause the causing {@link IOException}
   */
  public HttpStubCreationException(IOException cause) {
    super(cause);
  }
}
