package org.stubit.random;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.stubit.random.RandomIntBuilder.*;

import org.junit.jupiter.api.Test;

class RandomIntBuilderTest {

  @Test
  void anInt_initializer() {
    int result = anInt().build();
    assertThat(result).isBetween(MIN_VALUE, MAX_VALUE);
  }

  @Test
  void aPositiveInt_initializer() {
    int result = aPositiveInt().build();
    assertThat(result).isBetween(1, MAX_VALUE);
  }

  @Test
  void aNegativeInt_initializer() {
    int result = aNegativeInt().build();
    assertThat(result).isBetween(MIN_VALUE, 1);
  }

  @Test
  void min() {
    int min = 10;
    int result = anInt().min(min).build();
    assertThat(result).isGreaterThanOrEqualTo(min);
  }

  @Test
  void min_MAX_VALUE_minus_1() {
    int min = MAX_VALUE - 1;
    int result = anInt().min(min).build();
    assertThat(result).isEqualTo(min);
  }

  @Test
  void max() {
    int max = 10;
    int result = anInt().max(max).build();
    assertThat(result).isLessThanOrEqualTo(max);
  }

  @Test
  void max_MIN_VALUE() {
    int max = MIN_VALUE;
    int result = anInt().max(max).build();
    assertThat(result).isEqualTo(max);
  }

  @Test
  void max_less_than_min() {
    int min = 42;
    assertThatIllegalArgumentException()
        .isThrownBy(() -> anInt().min(min).max(min - 1))
        .withMessage("Can't set max to 41, as it must not be less than min (42)");
  }

  @Test
  void min_greater_than_min() {
    int max = 42;
    assertThatIllegalArgumentException()
        .isThrownBy(() -> anInt().max(max).min(max + 1))
        .withMessage("Can't set min to 43, as it must not be greater than max (42)");
  }

  @Test
  void max_MAX_VALUE() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> anInt().max(MAX_VALUE))
        .withMessage("Can't set max to 2147483647 (Integer.MAX_VALUE)");
  }
}
