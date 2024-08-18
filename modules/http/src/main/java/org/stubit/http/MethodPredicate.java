package org.stubit.http;

import static java.lang.String.join;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.jspecify.annotations.NullMarked;

/**
 * A {@link MethodPredicate} specifies which request methods should be considered for a {@link
 * Stubbing}.
 *
 * @param pattern the regular expression defining the considered methods
 */
@NullMarked
record MethodPredicate(Pattern pattern) implements Predicate<HttpRequest> {

  /**
   * Defines a predicate that will be true if the request method is the given method or is contained
   * in furtherMethods.
   *
   * @param method the method to be considered
   * @param furtherMethods optional further methods to be considered
   */
  public MethodPredicate(String method, String... furtherMethods) {
    this(Pattern.compile("^%s|%s$".formatted(method, join("|", furtherMethods))));
  }

  @Override
  public boolean test(HttpRequest request) {
    return pattern.matcher(request.method()).matches();
  }
}
