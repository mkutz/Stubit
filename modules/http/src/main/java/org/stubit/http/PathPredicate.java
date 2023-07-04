package org.stubit.http;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public record PathPredicate(Pattern pattern) implements Predicate<HttpRequest> {

  public PathPredicate(String path) {
    this(Pattern.compile("^%s.*$".formatted(path)));
  }

  @Override
  public boolean test(HttpRequest request) {
    return pattern.matcher(request.uri().getPath()).matches();
  }
}
