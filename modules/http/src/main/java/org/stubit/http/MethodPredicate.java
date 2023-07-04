package org.stubit.http;

import static java.lang.String.join;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public record MethodPredicate(Pattern pattern) implements Predicate<HttpRequest> {

  public MethodPredicate(String method, String... furtherMethods) {
    this(Pattern.compile("^%s|%s$".formatted(method, join("|", furtherMethods))));
  }

  @Override
  public boolean test(HttpRequest request) {
    return pattern.matcher(request.method()).matches();
  }
}
