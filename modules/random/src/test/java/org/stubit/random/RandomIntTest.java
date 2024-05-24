package org.stubit.random;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class RandomIntTest {

  @Test
  void anIntBetween() {
    int min = 42;
    int max = 4711;
    assertThat(RandomInt.anIntBetween(min, max)).isBetween(min, max);
  }

  @Test
  void anIntBetween_min_equal_to_max() {
    int min = 42;
    assertThat(RandomInt.anIntBetween(min, min)).isEqualTo(min);
  }

  @Test
  void anIntBetween_min_greater_than_max() {
    int max = 42;
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomInt.anIntBetween(max + 1, max))
        .withMessage("Can't set max to 42, as it must not be less than min (43)");
  }

  @Test
  void aPositiveInt() {
    assertThat(RandomInt.aPositiveInt()).isBetween(1, MAX_VALUE);
  }

  @Test
  void aNegativeInt() {
    assertThat(RandomInt.aNegativeInt()).isBetween(MIN_VALUE, 1);
  }
}
