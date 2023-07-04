package org.stubit.http;

import java.util.function.Predicate;

public record HttpStubbing(Predicate<HttpRequest> predicate, StubbedResponse response) {

  public static Builder stub() {
    return new Builder();
  }

  public static class Builder {
    private PathPredicate pathPredicate;
    private MethodPredicate methodPredicate;

    public Builder path(String path) {
      pathPredicate = new PathPredicate(path);
      return this;
    }

    public Builder method(String method, String... furtherMethods) {
      methodPredicate = new MethodPredicate(method, furtherMethods);
      return this;
    }

    public HttpStubbing response(StubbedResponse response) {
      final Predicate<HttpRequest> predicate =
          httpRequest ->
              (pathPredicate == null || pathPredicate.test(httpRequest))
                  && (methodPredicate == null || methodPredicate.test(httpRequest));
      return new HttpStubbing(predicate, response);
    }
  }
}
