package org.stubit.random;

import static java.lang.Long.MAX_VALUE;
import static java.lang.Long.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class RandomLongBuilderTest {

  @Test
  void aLong() {
    assertThat(RandomLong.aLong().build()).isBetween(MIN_VALUE, MAX_VALUE);
  }

  @Test
  void min() {
    long min = 10L;
    assertThat(RandomLong.aLong().min(min).build()).isGreaterThanOrEqualTo(min);
  }

  @Test
  void min_MAX_VALUE_minus_1() {
    long min = MAX_VALUE - 1;
    assertThat(RandomLong.aLong().min(min).build()).isEqualTo(min);
  }

  @Test
  void max() {
    long max = 10L;
    assertThat(RandomLong.aLong().max(max).build()).isLessThanOrEqualTo(max);
  }

  @Test
  void max_MIN_VALUE() {
    long max = MIN_VALUE;
    assertThat(RandomLong.aLong().max(max).build()).isEqualTo(max);
  }

  @Test
  void max_less_than_min() {
    long min = 42L;
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomLong.aLong().min(min).max(min - 1))
        .withMessage("Can't set max to 41, as it must not be less than min (42)");
  }

  @Test
  void min_greater_than_max() {
    long max = 42L;
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomLong.aLong().max(max).min(max + 1))
        .withMessage("Can't set min to 43, as it must not be greater than max (42)");
  }

  @Test
  void max_MAX_VALUE() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomLong.aLong().max(MAX_VALUE))
        .withMessage("Can't set max to 9223372036854775807 (Long.MAX_VALUE)");
  }
}
