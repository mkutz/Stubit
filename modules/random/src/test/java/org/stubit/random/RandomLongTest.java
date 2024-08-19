package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class RandomLongTest {

  @Test
  void aLongBetween() {
    long min = 42L;
    long max = Integer.MAX_VALUE + 1L;
    assertThat(RandomLong.aLongBetween(min, max)).isBetween(min, max);
  }

  @Test
  void aLongBetween_min_equal_to_max() {
    long min = 42L;
    assertThat(RandomLong.aLongBetween(min, min)).isEqualTo(min);
  }

  @Test
  void aLongBetween_min_greater_than_max() {
    long max = 42L;
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomLong.aLongBetween(max + 1, max))
        .withMessage("Can't set max to 42, as it must not be less than min (43)");
  }

  @Test
  void aPositiveLong() {
    assertThat(RandomLong.aPositiveLong()).isBetween(1L, Long.MAX_VALUE);
  }

  @Test
  void aNegativeLong() {
    assertThat(RandomLong.aNegativeLong()).isBetween(Long.MIN_VALUE, 1L);
  }
}
