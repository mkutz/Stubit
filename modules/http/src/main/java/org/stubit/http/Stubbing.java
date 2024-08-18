package org.stubit.http;

import java.util.function.Predicate;
import org.jspecify.annotations.NullMarked;

/**
 * A {@link Stubbing} defines that all requests that match the given {@link #predicate} should
 * return the given {@link #response()}
 *
 * @param predicate {@link Predicate} that needs to be true for this {@link Stubbing} to be
 *     considered
 * @param response the {@link StubbedResponse} that will be returned if the {@link #predicate} is
 *     true
 */
@NullMarked
public record Stubbing(Predicate<HttpRequest> predicate, StubbedResponse response) {

  /**
   * @return a newly created {@link Builder}
   */
  public static Builder stub() {
    return new Builder();
  }

  /** A fluent API builder for {@link Stubbing}. */
  public static class Builder {
    private PathPredicate pathPredicate;
    private MethodPredicate methodPredicate;

    /**
     * @param path the path that will be considered for the {@link Stubbing}.
     * @return this
     * @see PathPredicate#PathPredicate(String)
     */
    public Builder path(String path) {
      pathPredicate = new PathPredicate(path);
      return this;
    }

    /**
     * @param method the method to be considered
     * @param furtherMethods optional further methods to be considered
     * @return this
     * @see MethodPredicate#MethodPredicate(String, String...)
     */
    public Builder method(String method, String... furtherMethods) {
      methodPredicate = new MethodPredicate(method, furtherMethods);
      return this;
    }

    /**
     * Sets the response and builds the {@link Stubbing}.
     *
     * @param response the {@link StubbedResponse} to be used for the {@link Stubbing}
     * @return the build {@link Stubbing}
     */
    public Stubbing returns(StubbedResponse response) {
      final Predicate<HttpRequest> predicate =
          httpRequest ->
              (pathPredicate == null || pathPredicate.test(httpRequest))
                  && (methodPredicate == null || methodPredicate.test(httpRequest));
      return new Stubbing(predicate, response);
    }
  }
}
