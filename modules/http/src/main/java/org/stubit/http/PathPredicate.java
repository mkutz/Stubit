package org.stubit.http;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.jspecify.annotations.NullMarked;

/**
 * A {@link PathPredicate} specifies which request paths should be considered for a {@link
 * Stubbing}.
 *
 * @param pattern the regular expression defining the considered paths
 */
@NullMarked
record PathPredicate(Pattern pattern) implements Predicate<HttpRequest> {

  /**
   * Defines a predicate that is true if the request path in prefixed with the given path.
   *
   * <p>E.g. if path is <code>some/things</code>, paths like <code>some/things/4711</code> or <code>
   * some/things/awesome</code> will be considered for the stub.
   *
   * @param path the path prefix.
   */
  public PathPredicate(String path) {
    this(Pattern.compile("^%s.*$".formatted(path)));
  }

  @Override
  public boolean test(HttpRequest request) {
    return pattern.matcher(request.uri().getPath()).matches();
  }
}
