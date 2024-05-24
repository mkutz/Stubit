package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class RandomChoiceTest {

  @Test
  void anyOf_ellipsis() {
    String[] choices = {"a", "b", "c"};
    assertThat(RandomChoice.anyOf(choices)).isIn((Object[]) choices);
  }

  @Test
  void anyOf_ellipsis_single() {
    var single = "a";
    assertThat(RandomChoice.anyOf(single)).isEqualTo(single);
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
    assertThat(RandomChoice.anyOf(choices)).isIn(choices);
  }

  @Test
  void anyOf_collection_single() {
    var single = "a";
    assertThat(RandomChoice.anyOf(List.of(single))).isEqualTo(single);
  }

  @Test
  void anyOf_collection_empty() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomChoice.anyOf(List.of()))
        .withMessage("No choices provided");
  }

  @Test
  void anyOf_map() {
    var choices = Map.of("a", 1, "b", 2, "c", 3);
    assertThat(RandomChoice.anyOf(choices)).isIn(choices.entrySet());
  }

  @Test
  void anyOf_single() {
    var single = Map.entry("a", 1);
    assertThat(RandomChoice.anyOf(Map.of(single.getKey(), single.getValue()))).isEqualTo(single);
  }

  @Test
  void anyOf_map_empty() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomChoice.anyOf(Map.of()))
        .withMessage("No choices provided");
  }

  @Test
  void anyOfValuesOf_enum() {
    enum ChoiceEnum {
      A,
      B,
      C
    }

    assertThat(RandomChoice.any(ChoiceEnum.class)).isIn((Object[]) ChoiceEnum.values());
  }
}
