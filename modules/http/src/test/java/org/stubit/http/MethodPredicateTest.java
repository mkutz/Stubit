package org.stubit.http;

import static org.assertj.core.api.Assertions.assertThat;
import static org.stubit.http.HttpRequestTestDataBuilder.httpRequest;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

class MethodPredicateTest {

  @Test
  void test() {
    var predicate = new MethodPredicate("GET");

    assertThat(predicate.test(httpRequest().method("GET").build())).isTrue();
    assertThat(predicate.test(httpRequest().method("POST").build())).isFalse();
    assertThat(predicate.test(httpRequest().method("PUT").build())).isFalse();
    assertThat(predicate.test(httpRequest().method("DELETE").build())).isFalse();
  }

  @Test
  void test_multiple_methods() {
    var predicate = new MethodPredicate("POST", "PUT");

    assertThat(predicate.test(httpRequest().method("GET").build())).isFalse();
    assertThat(predicate.test(httpRequest().method("POST").build())).isTrue();
    assertThat(predicate.test(httpRequest().method("PUT").build())).isTrue();
    assertThat(predicate.test(httpRequest().method("DELETE").build())).isFalse();
  }

  @Test
  void test_pattern() {
    var predicate = new MethodPredicate(Pattern.compile(".+E.+"));

    assertThat(predicate.test(httpRequest().method("GET").build())).isTrue();
    assertThat(predicate.test(httpRequest().method("POST").build())).isFalse();
    assertThat(predicate.test(httpRequest().method("PUT").build())).isFalse();
    assertThat(predicate.test(httpRequest().method("DELETE").build())).isTrue();
  }
}
