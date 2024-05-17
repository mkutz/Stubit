package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.stubit.random.RandomChoice.anyOf;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class RandomChoiceTest {

  @Test
  void anyOf_ellipsis() {
    String[] choices = {"a", "b", "c"};
    assertThat(anyOf(choices)).isIn(choices);
  }

  @Test
  void anyOf_ellipsis_single() {
    var single = "a";
    assertThat(anyOf(single)).isEqualTo(single);
  }

  @Test
  void anyOf_ellipsis_empty() {
    assertThatIllegalArgumentException()
        .isThrownBy(RandomChoice::anyOf)
        .withMessage("No choices provided");
  }

  @Test
  void anyOf_collection() {
    var choices = List.of("a", "b", "c");
    assertThat(anyOf(choices)).isIn("a", "b", "c");
  }

  @Test
  void anyOf_collection_single() {
    var single = "a";
    assertThat(anyOf(List.of(single))).isEqualTo(single);
  }

  @Test
  void anyOf_collection_empty() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> anyOf(List.of()))
        .withMessage("No choices provided");
  }

  @Test
  void anyOf_map() {
    var choices = Map.of("a", 1, "b", 2, "c", 3);
    assertThat(anyOf(choices)).isIn(choices.entrySet());
  }

  @Test
  void anyOf_single() {
    var single = Map.entry("a", 1);
    assertThat(anyOf(Map.of(single.getKey(), single.getValue()))).isEqualTo(single);
  }

  @Test
  void anyOf_map_empty() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> anyOf(Map.of()))
        .withMessage("No choices provided");
  }
}
