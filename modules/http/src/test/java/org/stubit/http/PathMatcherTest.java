package org.stubit.http;

import static org.assertj.core.api.Assertions.assertThat;
import static org.stubit.http.HttpRequestTestDataBuilder.httpRequest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PathMatcherTest {

  @ParameterizedTest(name = "test({0}) == true")
  @ValueSource(
      strings = {
        "/some/thing/",
        "/some/thing/great",
        "/some/thing/4711",
        "/some/thing/amazing/4711/"
      })
  void testPositive(String path) {
    var predicate = new PathPredicate("/some/thing/.*");

    assertThat(predicate.test(httpRequest().path(path).build())).isTrue();
  }

  @ParameterizedTest(name = "test({0}) == false")
  @ValueSource(
      strings = {
        "/some/",
        "/something/",
        "/some/4711/thing",
        "/wrong",
        "/thing/some/",
        "/thing/some/",
      })
  void testNegative(String path) {
    var predicate = new PathPredicate("/some/thing/.*");

    assertThat(predicate.test(httpRequest().path(path).build())).isFalse();
  }
}
