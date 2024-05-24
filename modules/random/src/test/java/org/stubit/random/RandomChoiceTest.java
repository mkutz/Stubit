package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class RandomChoiceTest {

  @Test
  void chooseAnyFrom_ellipsis() {
    String[] choices = {"a", "b", "c"};
    assertThat(RandomChoice.chooseAnyFrom(choices)).isIn((Object[]) choices);
  }

  @Test
  void chooseAnyFrom_ellipsis_single() {
    var single = "a";
    assertThat(RandomChoice.chooseAnyFrom(single)).isEqualTo(single);
  }

  @Test
  void chooseAnyFrom_ellipsis_empty() {
    assertThatIllegalArgumentException()
        .isThrownBy(RandomChoice::chooseAnyFrom)
        .withMessage("No choices provided");
  }

  @Test
  void chooseAnyFrom_collection() {
    var choices = List.of("a", "b", "c");
    assertThat(RandomChoice.chooseAnyFrom(choices)).isIn(choices);
  }

  @Test
  void chooseAnyFrom_collection_single() {
    var single = "a";
    assertThat(RandomChoice.chooseAnyFrom(List.of(single))).isEqualTo(single);
  }

  @Test
  void chooseAnyFrom_collection_empty() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomChoice.chooseAnyFrom(List.of()))
        .withMessage("No choices provided");
  }

  @Test
  void chooseAnyFrom_map() {
    var choices = Map.of("a", 1, "b", 2, "c", 3);
    assertThat(RandomChoice.chooseAnyFrom(choices)).isIn(choices.entrySet());
  }

  @Test
  void chooseAnyFrom_single() {
    var single = Map.entry("a", 1);
    assertThat(RandomChoice.chooseAnyFrom(Map.of(single.getKey(), single.getValue())))
        .isEqualTo(single);
  }

  @Test
  void chooseAnyFrom_map_empty() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomChoice.chooseAnyFrom(Map.of()))
        .withMessage("No choices provided");
  }

  @Test
  void chooseAnyFromValuesOf_enum() {
    enum ChoiceEnum {
      A,
      B,
      C
    }

    assertThat(RandomChoice.chooseAnyFromValuesOf(ChoiceEnum.class))
        .isIn((Object[]) ChoiceEnum.values());
  }
}
