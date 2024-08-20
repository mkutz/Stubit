package org.stubit.random;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class RandomIntBuilderTest {

  @Test
  void anInt() {
    assertThat(RandomInt.anInt().build()).isBetween(MIN_VALUE, MAX_VALUE);
  }

  @Test
  void min() {
    int min = 10;
    assertThat(RandomInt.anInt().min(min).build()).isGreaterThanOrEqualTo(min);
  }

  @Test
  void min_MAX_VALUE_minus_1() {
    int min = MAX_VALUE - 1;
    assertThat(RandomInt.anInt().min(min).build()).isEqualTo(min);
  }

  @Test
  void max() {
    int max = 10;
    assertThat(RandomInt.anInt().max(max).build()).isLessThanOrEqualTo(max);
  }

  @Test
  void min_greater_than_max() {
    int max = 42;
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomInt.anInt().max(max).min(max + 1))
        .withMessage("Can't set min to 43, as it must not be greater than max (42)");
  }

  @Test
  void max_MIN_VALUE() {
    int max = MIN_VALUE;
    assertThat(RandomInt.anInt().max(max).build()).isEqualTo(max);
  }

  @Test
  void max_less_than_min() {
    int min = 42;
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomInt.anInt().min(min).max(min - 1))
        .withMessage("Can't set max to 41, as it must not be less than min (42)");
  }

  @Test
  void max_MAX_VALUE() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomInt.anInt().max(MAX_VALUE))
        .withMessage("Can't set max to 2147483647 (Integer.MAX_VALUE)");
  }
}
