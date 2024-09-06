package org.stubit.random;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class RandomNumberTest {

  @Test
  void anIntBetween() {
    int min = 42;
    int max = 4711;
    assertThat(RandomNumber.anIntBetween(min, max)).isBetween(min, max);
  }

  @Test
  void anIntBetween_min_equal_to_max() {
    int min = 42;
    assertThat(RandomNumber.anIntBetween(min, min)).isEqualTo(min);
  }

  @Test
  void anIntBetween_min_greater_than_max() {
    int max = 42;
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomNumber.anIntBetween(max + 1, max))
        .withMessage("Can't set max to 42, as it must not be less than min (43)");
  }

  @Test
  void aPositiveInt() {
    assertThat(RandomNumber.aPositiveInt()).isBetween(1, MAX_VALUE);
  }

  @Test
  void aNegativeInt() {
    assertThat(RandomNumber.aNegativeInt()).isBetween(MIN_VALUE, 1);
  }

  @Test
  void aLongBetween() {
    long min = 42L;
    long max = Integer.MAX_VALUE + 1L;
    assertThat(RandomNumber.aLongBetween(min, max)).isBetween(min, max);
  }

  @Test
  void aLongBetween_min_equal_to_max() {
    long min = 42L;
    assertThat(RandomNumber.aLongBetween(min, min)).isEqualTo(min);
  }

  @Test
  void aLongBetween_min_greater_than_max() {
    long max = 42L;
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomNumber.aLongBetween(max + 1, max))
        .withMessage("Can't set max to 42, as it must not be less than min (43)");
  }

  @Test
  void aPositiveLong() {
    assertThat(RandomNumber.aPositiveLong()).isBetween(1L, Long.MAX_VALUE);
  }

  @Test
  void aNegativeLong() {
    assertThat(RandomNumber.aNegativeLong()).isBetween(Long.MIN_VALUE, 1L);
  }
}
